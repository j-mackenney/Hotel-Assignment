<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : users.xsl
    Created on : 20 September 2016, 6:24 PM
    Author     : minniemanZ
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
 xmlns:x="http://www.uts.edu.au/31284/wsd-diary" exclude-result-prefixes="x">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:apply-templates/> 
    </xsl:template>
    
    <xsl:template match="x:users">
        <table>
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Name</th>
                    <th>Password</th>
                </tr>
            </thead>
            <tbody>
                <xsl:apply-templates/>
            </tbody>
        </table>
    </xsl:template>
    
    <xsl:template match="user">
        <tr><xsl:apply-templates/></tr>
    </xsl:template>
    
    <xsl:template match="username|email|name|password">
        <td><xsl:apply-templates/></td>
    </xsl:template>

</xsl:stylesheet>
