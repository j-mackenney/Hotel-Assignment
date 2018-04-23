<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : userListings.xsl
    Created on : 20 September 2016, 6:24 PM
    Author     : minniemanZ
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
                xmlns:x="http://www.uts.edu.au/31284/wsd-diary/listings" exclude-result-prefixes="x">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:apply-templates/> 
    </xsl:template>
    
    <xsl:template match="x:listings">
        <xsl:apply-templates/>
        <xsl:choose>
            <xsl:when test="count(listing) = 0">
                <div class="infoAlert">You currently have no listings.</div>
            </xsl:when>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="listing">
        <div class="userListing">
            <a href="listing.jsp?id={id}">
                <img class="houseplaceholder" src="house.png" />
            </a>
            <div class="info">
                <p>Type: <xsl:value-of select="type"/></p>
                <p>Guests: <xsl:value-of select="guests"/></p>
                <p>Status: 
                    <xsl:choose>
                        <xsl:when test="status = 'available'">
                            <span class="greenStatus"> 
                                <xsl:value-of select="status"/>
                            </span>
                        </xsl:when>
                        <xsl:otherwise>
                            <span class="redStatus"> 
                                <xsl:value-of select="status"/>
                            </span>
                        </xsl:otherwise>
                    </xsl:choose>
                </p>
                <xsl:apply-templates/>
            </div>
            <xsl:choose>
                <xsl:when test="status = 'available'">
                    <div class="buttons">
                        <a class="button red" href="closeListing.jsp?id={id}">Close listing</a>
                        <a class="button green" href="enquiries.jsp?id={id}">View enquiries</a>
                    </div>
                </xsl:when>
            </xsl:choose>
        </div>
    </xsl:template>
    
    <xsl:template match="location">
        <p>Location: <xsl:value-of select="suburb"/>, <xsl:value-of select="state"/></p>
    </xsl:template>
    
    <xsl:template match="id" />
    <xsl:template match="status" />
    <xsl:template match="type" />
    <xsl:template match="guests" />
    <xsl:template match="listerUsername" />
    <xsl:template match="description" />

</xsl:stylesheet>
