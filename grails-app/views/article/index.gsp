<!DOCTYPE html>
<html>
<head>
   <meta name="layout" content="miki"/>
   <title>Articles</title>
</head>
<body>
   <g:each var="entry" in="${ articles }">
      <p><g:link action="view" params="[ file: entry.filename ]">${ entry.human }</g:link></p>
   </g:each>
</body>
</html>