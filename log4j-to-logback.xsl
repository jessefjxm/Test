<!--
     This XLST script converts log4j.xml.bak file to logback.xml file
     trying to mimic its behavior as close as possible

     @author Roman Puchkovskiy
 -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                exclude-result-prefixes="log4j xalan"
                xmlns:log4j="http://jakarta.apache.org/log4j/"
                xmlns:xalan="http://xml.apache.org/xslt"
                xmlns:xslt="http://www.w3.org/1999/XSL/Transform">

    <xsl:output indent="yes" xalan:indent-amount="4"/>

    <xsl:variable name="vLower" select="'abcdefghijklmnopqrstuvwxyz'"/>

    <xsl:variable name="vUpper" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>

    <xsl:template match="/log4j:configuration">
        <configuration>
            <xsl:apply-templates select="appender"/>
            <xsl:apply-templates select="logger"/>
            <xsl:apply-templates select="root"/>
            <xsl:apply-templates select="comment()"/>
        </configuration>
    </xsl:template>

    <xsl:template match="appender">
        <appender>
            <xsl:attribute name="name">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
            <xsl:attribute name="class">
                <xsl:choose>
                    <!-- 定义了Appender 之间的映射关系 -->
                    <xsl:when test="@class = 'org.apache.log4j.DailyRollingFileAppender'">ch.qos.logback.core.rolling.RollingFileAppender</xsl:when>
                    <xsl:when test="@class = 'com.alibaba.common.logging.spi.log4j.DailyRollingFileAppender'">ch.qos.logback.core.rolling.RollingFileAppender</xsl:when>
                    <xsl:when test="@class = 'org.apache.log4j.AsyncAppender'">ch.qos.logback.classic.AsyncAppender</xsl:when>
                    <xsl:when test="@class = 'org.apache.log4j.ConsoleAppender'">ch.qos.logback.core.ConsoleAppender</xsl:when>
                    <xsl:when test="@class = 'org.apache.log4j.net.SMTPAppender'">ch.qos.logback.classic.net.SMTPAppender</xsl:when>
                    <xsl:when test="@class = 'org.apache.log4j.net.SocketAppender'">ch.qos.logback.classic.net.SocketAppender</xsl:when>
                    <xsl:when test="@class = 'org.apache.log4j.net.SyslogAppender'">ch.qos.logback.classic.net.SyslogAppender</xsl:when>
                    <xsl:otherwise>
                        <xsl:message terminate="yes">Unknown appender class:
                            <xsl:value-of select="@class"/>
                        </xsl:message>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <xsl:apply-templates select="param"/>
            <xsl:apply-templates select="layout"/>
            <xsl:apply-templates select="filter"/>
        </appender>
        <xsl:call-template name="newline"/>
    </xsl:template>

    <xsl:template match="param">
        <xsl:choose>
            <!-- 将log4j file 转换为 logback 的file -->
            <xsl:when test="@name = 'file'">
                <xsl:element name="{concat(translate(substring(@name,1,1), $vUpper, $vLower),substring(@name, 2))}">
                    <xsl:value-of select="@value"/>
                </xsl:element>
            </xsl:when>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="layout">
        <xsl:choose>
            <!-- 将PatternLayout 做转换 -->
            <xsl:when test="@class = 'org.apache.log4j.PatternLayout'">
                <xsl:choose>
                    <xsl:when
                            test="../@class = 'org.apache.log4j.ConsoleAppender' or ../@class = 'org.apache.log4j.DailyRollingFileAppender' or ../@class = 'com.alibaba.common.logging.spi.log4j.DailyRollingFileAppender'">
                        <encoder>
                            <pattern>
                                <xsl:value-of select="param[@name = 'ConversionPattern']/@value"/>
                            </pattern>
                            <charset>UTF-8</charset>
                        </encoder>
                        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
                            <xsl:element name="fileNamePattern">
                                <xsl:value-of select="../param[@name = 'file']/@value" />.%d{yyyy-MM-dd}</xsl:element>
                            <maxHistory>7</maxHistory>
                        </rollingPolicy>
                    </xsl:when>
                    <xsl:when
                            test="../@class = 'org.apache.log4j.net.SocketAppender' or ../@class = 'org.apache.log4j.net.SyslogAppender'">
                        <xsl:comment>this is NOT needed tor this logger, so it is commented out</xsl:comment>
                        <xsl:comment><![CDATA[
                            <layout>
                                <pattern>]]><xsl:value-of select="param[@name = 'ConversionPattern']/@value"/><![CDATA[</pattern>
                            </layout>]]>
                        </xsl:comment>
                    </xsl:when>
                    <xsl:otherwise>
                        <layout>
                            <pattern>
                                <xsl:value-of select="param[@name = 'ConversionPattern']/@value"/>
                            </pattern>
                        </layout>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:when>
            <xsl:otherwise>
                <xsl:message terminate="yes">Unknown layout class:
                    <xsl:value-of select="@class"/>
                </xsl:message>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="filter">
        <xsl:choose>
            <xsl:when
                    test="@class = 'org.apache.log4j.varia.LevelRangeFilter' and param[@name = 'LevelMin']/@value != '' and param[@name = 'LevelMax']/@value = 'FATAL'">
                <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
                    <level>
                        <xsl:value-of select="param[@name = 'LevelMin']/@value"/>
                    </level>
                </filter>
            </xsl:when>
            <xsl:otherwise>
                <xsl:message terminate="yes">Don't know what to do with filter</xsl:message>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

    <xsl:template match="logger">
        <logger>
            <xsl:attribute name="name">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
            <xsl:attribute name="level">
                <xsl:choose>
                    <xsl:when test="level/@value = 'FATAL'">OFF</xsl:when>
                    <xsl:otherwise>
                        <xsl:value-of select="level/@value"/>
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:attribute>
            <xsl:if test="@additivity != ''">
                <xsl:attribute name="additivity">
                    <xsl:value-of select="@additivity"/>
                </xsl:attribute>
            </xsl:if>
            <xsl:apply-templates select="appender-ref"/>
        </logger>
    </xsl:template>

    <xsl:template match="appender-ref">
        <appender-ref>
            <xsl:attribute name="ref">
                <xsl:value-of select="@ref"/>
            </xsl:attribute>
        </appender-ref>
    </xsl:template>

    <xsl:template match="root">
        <xsl:call-template name="newline"/>
        <root>
            <xsl:attribute name="level">
                <xsl:value-of select="level/@value"/>
            </xsl:attribute>
            <xsl:apply-templates select="appender-ref"/>
            <xsl:apply-templates select="comment()"/>
        </root>
    </xsl:template>

    <xsl:template match="comment()">
        <xsl:copy-of select="."/>
    </xsl:template>

    <xsl:template name="newline">
        <!-- don't reformat this! -->
        <xsl:text>

        </xsl:text>
    </xsl:template>

</xsl:stylesheet>