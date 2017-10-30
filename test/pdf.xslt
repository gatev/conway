<?xml version="1.0" encoding="UTF-8"?>
<!-- New XSL-FO document created with EditiX XML Editor (http://www.editix.com) at Thu Oct 26 10:06:26 EEST 2017 -->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.1" exclude-result-prefixes="fo">
   <xsl:output method="xml" version="2.0" omit-xml-declaration="no" indent="yes" />
   <xsl:key name="arr_columns_alignment" match="table/thead/tr[last()]/td/@align" use="1" />
   <xsl:variable name="rotate_page">
      <xsl:choose>
         <xsl:when test="artifact/@rotate='yes'">1</xsl:when>
         <xsl:otherwise>0</xsl:otherwise>
      </xsl:choose>
   </xsl:variable>
   <xsl:template match="artifact/worksheet">
      <fo:root>
         <fo:layout-master-set>
            <fo:simple-page-master master-name="simpleA4" margin-top="2cm" margin-bottom="2cm" margin-left="1cm" margin-right="1cm">
               <xsl:choose>
                  <xsl:when test="$rotate_page = 1">
                     <xsl:attribute name="page-height">21cm</xsl:attribute>
                     <xsl:attribute name="page-width">29.7cm</xsl:attribute>
                  </xsl:when>
                  <xsl:otherwise>
                     <xsl:attribute name="page-height">29.7cm</xsl:attribute>
                     <xsl:attribute name="page-width">21cm</xsl:attribute>
                  </xsl:otherwise>
               </xsl:choose>
               <fo:region-body margin-top="2.1cm" margin-bottom="1.6cm" />
               <fo:region-before extent="2cm" />
               <fo:region-after extent="1.5cm" />
            </fo:simple-page-master>
         </fo:layout-master-set>
         <fo:page-sequence master-reference="simpleA4" initial-page-number="1">
            <fo:static-content flow-name="xsl-region-before">
               <xsl:apply-templates select="header" />
            </fo:static-content>
            <fo:static-content flow-name="xsl-region-after">
               <fo:block text-align="center" font-family="Arial" font-size="10pt">
                  Страница
                  <fo:page-number />
                  от
                  <fo:page-number-citation ref-id="last-page" />
               </fo:block>
            </fo:static-content>
            <fo:flow flow-name="xsl-region-body">
               <xsl:apply-templates select="p|text|table|tbl" />
               <fo:block id="last-page" />
            </fo:flow>
         </fo:page-sequence>
      </fo:root>
   </xsl:template>
   <xsl:template match="header">
      <xsl:apply-templates select="p" />
   </xsl:template>
   <xsl:template match="p">
      <xsl:if test="string(.) = ''">
          <fo:block>&#160;</fo:block>
      </xsl:if>
      <xsl:if test="string(.) != ''">
         <fo:block font-family="Arial">
         <xsl:choose>
            <xsl:when test="name(../.) = 'header'">
               <xsl:attribute name="text-align">center</xsl:attribute>
            </xsl:when>
            <xsl:when test="@align">
               <xsl:attribute name="text-align">
                  <xsl:value-of select="@align" />
               </xsl:attribute>
            </xsl:when>
         </xsl:choose>
         <xsl:choose>
            <xsl:when test="@font-size">
               <xsl:attribute name="font-size">
                  <xsl:value-of select="@font-size" />
                  pt
               </xsl:attribute>
            </xsl:when>
            <xsl:otherwise>
               <xsl:if test="not(name(../.) = 'header')">
                  <xsl:attribute name="font-size">8pt</xsl:attribute>
               </xsl:if>
            </xsl:otherwise>
         </xsl:choose>
         <xsl:apply-templates select="text()" />
         <xsl:if test="not(name(../.) = 'header')"> </xsl:if>
      </fo:block> 
      </xsl:if>
      
   </xsl:template>
   <xsl:template match="table">
       <fo:block font-family="Arial" font-size="6pt">
         <fo:table table-layout="fixed">
            
            <xsl:for-each select="thead/tr[last()]/td">
               <fo:table-column>
                  <xsl:attribute name="column-width">
                     <xsl:choose>
                        <xsl:when test="$rotate_page = 1">
                           <xsl:value-of select="27.7 * translate(@width, '%', '') div 100" />
                           cm
                        </xsl:when>
                        <xsl:otherwise>
                           <xsl:value-of select="19 * translate(@width, '%', '') div 100" />
                           cm
                        </xsl:otherwise>
                     </xsl:choose>
                  </xsl:attribute>
               </fo:table-column>
            </xsl:for-each>
            <xsl:apply-templates select="thead" />
            <xsl:apply-templates select="tbody" />
         </fo:table>
      </fo:block> 
   </xsl:template>
   <xsl:template match="thead">
      <xsl:choose>
         <xsl:when test="tr">
            <fo:table-header background-color="lightgrey" font-family="Arial">
               <xsl:attribute name="font-size">
                  <xsl:choose>
                     <xsl:when test="@font-size">
                        <xsl:value-of select="@font-size" />
                        pt
                     </xsl:when>
                     <xsl:otherwise>8pt</xsl:otherwise>
                  </xsl:choose>
               </xsl:attribute>
               <xsl:apply-templates select="tr" />
            </fo:table-header>
         </xsl:when>
         <xsl:otherwise>
            <fo:table-header>
               <fo:table-row>
                  <fo:table-cell>
                     <fo:block />
                  </fo:table-cell>
               </fo:table-row>
            </fo:table-header>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>
   <xsl:template match="tbody">
      <fo:table-body font-family="Arial">
         <xsl:attribute name="font-size">
            <xsl:choose>
               <xsl:when test="@font-size">
                  <xsl:value-of select="@font-size" />
                  pt
               </xsl:when>
               <xsl:otherwise>6pt</xsl:otherwise>
            </xsl:choose>
         </xsl:attribute>
         <xsl:apply-templates select="tr" />
      </fo:table-body>
   </xsl:template>
   <xsl:template match="tr">
      <fo:table-row>
         <xsl:if test="@bgc">
            <xsl:attribute name="background-color">
               <xsl:value-of select="@bgc" />
            </xsl:attribute>
         </xsl:if>
         <xsl:if test="@font-size">
            <xsl:attribute name="font-size">
               <xsl:value-of select="@font-size" />
               pt
            </xsl:attribute>
         </xsl:if>
         <xsl:if test="@font-weight='bold'">
            <xsl:attribute name="font-weight">bold</xsl:attribute>
         </xsl:if>
         <xsl:attribute name="keep-together">always</xsl:attribute>
         <xsl:for-each select="td">
            <xsl:call-template name="td">
               <xsl:with-param name="td_position" select="position()" />
            </xsl:call-template>
         </xsl:for-each>
      </fo:table-row>
   </xsl:template>
   <xsl:template name="td">
      <xsl:param name="td_position" />
      <xsl:if test="not(@show='no')">
         <fo:table-cell>
            <xsl:attribute name="padding-left">2pt</xsl:attribute>
            <xsl:attribute name="padding-right">2pt</xsl:attribute>
            <xsl:attribute name="text-align">
               <xsl:choose>
                  <xsl:when test="name(../../.) = 'thead' and not(@align)">
                     <xsl:text>center</xsl:text>
                  </xsl:when>
                  <xsl:when test="@align">
                     <xsl:value-of select="@align" />
                  </xsl:when>
                  <xsl:otherwise>
                     <!--<xsl:value-of select="../../../thead/tr[last()]/td[$td_position]/@align" />-->
                     <xsl:value-of select="key('arr_columns_alignment', 1)[$td_position]" />
                  </xsl:otherwise>
               </xsl:choose>
            </xsl:attribute>
            <xsl:if test="@colspan">
               <xsl:attribute name="number-columns-spanned">
                  <xsl:value-of select="@colspan" />
               </xsl:attribute>
            </xsl:if>
            <xsl:if test="@rowspan">
               <xsl:attribute name="number-rows-spanned">
                  <xsl:value-of select="@rowspan" />
               </xsl:attribute>
            </xsl:if>
            <xsl:choose>
               <xsl:when test="position() = last()">
                  <xsl:attribute name="border-left-width">0.5pt</xsl:attribute>
                  <xsl:attribute name="border-right-width">0.5pt</xsl:attribute>
               </xsl:when>
               <xsl:otherwise>
                  <xsl:attribute name="border-left-width">0.5pt</xsl:attribute>
                  <xsl:attribute name="border-right-width">0pt</xsl:attribute>
               </xsl:otherwise>
            </xsl:choose>
            <xsl:if test="not(../../.././@border='no') and not(@border='no')">
               <xsl:attribute name="border-style">solid</xsl:attribute>
            </xsl:if>
            <fo:block wrap-option="wrap">
               <xsl:if test="@indent">
                  <xsl:attribute name="text-indent">
                     <xsl:value-of select="@indent" />
                     pt
                  </xsl:attribute>
               </xsl:if>
               <xsl:apply-templates select="text()|table|p" />
            </fo:block>
         </fo:table-cell>
      </xsl:if>
   </xsl:template>
   <xsl:template match="tbl">
      <xsl:variable name="columnWidth">
         <xsl:value-of select="@maxrows" />
         <xsl:choose>
            <xsl:when test="$rotate_page = 1">
               <xsl:value-of select="27.7 div @maxrows" />
               cm
            </xsl:when>
            <xsl:otherwise>
               <xsl:value-of select="19 div @maxrows" />
               cm
            </xsl:otherwise>
         </xsl:choose>
      </xsl:variable>
      <fo:block font-family="Arial" font-size="6pt">
         <fo:table table-layout="fixed">
            <xsl:call-template name="columnCounter">
               <xsl:with-param name="cnt" select="@maxrows" />
               <xsl:with-param name="columnWidth" select="$columnWidth" />
            </xsl:call-template>
            <fo:table-body>
               <xsl:apply-templates select="tblr" />
            </fo:table-body>
         </fo:table>
      </fo:block>
   </xsl:template>
   <xsl:template match="tblr">
      <fo:table-row keep-together="always">
         <xsl:if test="@bgc">
            <xsl:attribute name="background-color">
               <xsl:value-of select="@bgc" />
            </xsl:attribute>
         </xsl:if>
         <xsl:if test="@font-size">
            <xsl:attribute name="font-size">
               <xsl:value-of select="@font-size" />
               pt
            </xsl:attribute>
         </xsl:if>
         <xsl:if test="@font-weight='bold'">
            <xsl:attribute name="font-weight">bold</xsl:attribute>
         </xsl:if>
         <xsl:apply-templates select="tblc" />
      </fo:table-row>
   </xsl:template>
   <xsl:template match="tblc">
      <xsl:if test="not(@show='no')">
         <fo:table-cell>
            <xsl:attribute name="padding-left">2pt</xsl:attribute>
            <xsl:attribute name="padding-right">2pt</xsl:attribute>
            <xsl:attribute name="text-align">
               <xsl:if test="@align">
                  <xsl:value-of select="@align" />
               </xsl:if>
            </xsl:attribute>
            <xsl:if test="@colspan">
               <xsl:attribute name="number-columns-spanned">
                  <xsl:value-of select="@colspan" />
               </xsl:attribute>
            </xsl:if>
            <xsl:if test="@rowspan">
               <xsl:attribute name="number-rows-spanned">
                  <xsl:value-of select="@rowspan" />
               </xsl:attribute>
            </xsl:if>
            <fo:block hyphenate="true" language="en" white-space-collapse="false" linefeed-treatment="preserve">
               <xsl:if test="@indent">
                  <xsl:attribute name="text-indent">
                     <xsl:value-of select="@indent" />
                     pt
                  </xsl:attribute>
               </xsl:if>
               <xsl:apply-templates select="text()|tbl|table" />
            </fo:block>
         </fo:table-cell>
      </xsl:if>
   </xsl:template>
   <xsl:template name="columnCounter">
      <xsl:param name="cnt" />
      <xsl:param name="columnWidth" />
      <fo:table-column>
         <xsl:attribute name="column-width">
            <xsl:value-of select="$columnWidth" />
            cm
         </xsl:attribute>
      </fo:table-column>
      <xsl:if test="$cnt &gt; 1">
         <xsl:call-template name="columnCounter">
            <xsl:with-param name="cnt" select="$cnt - 1" />
            <xsl:with-param name="columnWidth" select="$columnWidth" />
         </xsl:call-template>
      </xsl:if>
   </xsl:template>
   <xsl:template match="text()">
      <xsl:value-of select="." />
   </xsl:template>
   <xsl:template name="intersperse-with-zero-spaces">
        <xsl:param name="str"/>
        <xsl:param name="max_length"/>

        <xsl:variable name="head" select="substring($str, 1, $max_length)" />
        <xsl:variable name="tail" select="substring($str, $max_length + 1)" />

        <xsl:value-of select="$head"/>

        <xsl:if test="$tail">
            <!-- there's no space present when translate() returns the same string and the $tail does not begin with a space, either  -->
            <xsl:if test="string-length(translate($head, ' ', '')) = string-length($head) and not(substring($tail, 1, 1)=' ')">
                <xsl:text>&#x200b;</xsl:text>
            </xsl:if>
            <xsl:call-template name="intersperse-with-zero-spaces">
                <xsl:with-param name="str"        select="$tail"/>
                <xsl:with-param name="max_length" select="$max_length"/>
            </xsl:call-template>
        </xsl:if>
    </xsl:template>
</xsl:stylesheet>
