<!DOCTYPE html>
<html>
<head>
   <meta name="layout" content="miki"/>
   <title>${ title }</title>
</head>
<body>
   <g:form action="save">
      <g:textField name="file" value="${title}"/>

      <g:textArea name="content" value="${ content }"/>
      <p><b>Tags</b> <g:textField name="tags" value="${ tags }" />
      <menu>
         <li><g:link action="index">Cancel</g:link></li>
         <li><g:submitButton name="save" value="Save" /></li>
      </menu>

      <!-- Hidden Inputs -->
      <g:textField name="created" value="${ created }" hidden="hidden"/>
   </g:form>
</body>
</html>