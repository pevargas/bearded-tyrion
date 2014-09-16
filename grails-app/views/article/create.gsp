<!DOCTYPE html>
<html>
<head>
   <meta name="layout" content="miki"/>
   <title>${ title }</title>
</head>
<body>
   <g:form action="save">
      <g:textField name="file" value="${title}"/>
      <menu>
         <li><g:link action="index">Cancel</g:link></li>
         <li><g:submitButton name="save" value="Save" /></li>
      </menu>
      <p><b>Tags</b> <g:textField name="tags" value="${ tags }" />
      <g:textField name="created" value="${ created }" hidden="hidden"/>
      <g:textArea name="content" value="${ content }"/>
   </g:form>
</body>
</html>