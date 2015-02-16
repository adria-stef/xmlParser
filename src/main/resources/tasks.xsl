<?xml version="1.0" encoding="UTF-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<body style="margin-top: 30px;margin-bottom: 100px;margin-right: 150px;margin-left: 150px;font-family:Arial;font-size:12pt;background-color:#EEEEEE">
		<xsl:for-each select="tasks/task">
			<div style="background-color:teal;color:white;padding:4px">
				<span style="font-weight:bold"><xsl:value-of select="number"/> - </span>
				<b><xsl:value-of select="topic"/></b>
			</div>		
			<div style="margin-top:10px;margin-left:20px;margin-bottom:10px;font-size:10pt">		
				<table>
				  <tr>
					<td><b>Due date:</b></td>
					<td><xsl:value-of select="due-date"/></td>
				  </tr>
				  <tr>
					<td><b>Instructions:</b></td> 
					<td><xsl:value-of select="instructions"/></td>
				  </tr>			  
				  <tr>
					<td><b style="font-style:italic">Condition:</b></td>
					<td><xsl:value-of select="condition"/></td>
				  </tr>
				  <tr>
					<td><b>Solution (file):</b></td>
					<td><xsl:value-of select="solution"/></td>
				  </tr>
				  <tr>
					<td><b>Maximum points:</b></td> 
					<td><xsl:value-of select="max-points"/></td>
				  </tr>
				</table>
			</div>
		</xsl:for-each>
	</body>
</html>