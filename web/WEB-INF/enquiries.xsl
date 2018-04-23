<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : enquiries.xsl
    Created on : 6 October 2016, 3:17 PM
    Author     : James
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:x="http://www.uts.edu.au/31284/wsd-diary/enquiries" exclude-result-prefixes="x">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <h1>Enquiries</h1>
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="x:enquiries">
        <xsl:apply-templates/>
        <xsl:choose>
            <xsl:when test="count(enquiry) = 0">
                <div class="infoAlert">This listing has no enquiries.</div>
            </xsl:when>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="enquiry">
        <div class="enquiry">
            <p>Name: <xsl:value-of select="name"/></p>
            <p>Email: <xsl:value-of select="email"/></p>
            <p>Start Date: <xsl:value-of select="startDate"/></p>
            <p>End Date: <xsl:value-of select="endDate"/></p>
            <p>Message: <xsl:value-of select="message"/></p>
            <p>Listing ID: <xsl:value-of select="listingId"/></p>
        </div>
    </xsl:template>
    
    <xsl:template match="name" />
    <xsl:template match="email" />
    <xsl:template match="startDate" />
    <xsl:template match="endDate" />
    <xsl:template match="message" />
    <xsl:template match="listingId" />
    
</xsl:stylesheet>
