<!DOCTYPE html>
<html>
<head>
   <meta name="layout" content="miki"/>
   <title>${ human }</title>
</head>
<body>
   <g:form action="save">
      <g:textField name="file" value="${ title }" />

      <g:textArea name="content" value="${ content }"/>

      <p><b>Created</b> ${ created } | <b>Last Updated</b> ${ updated } | <b>Tags</b> <g:textField name="tags" value="${ tags.join(',') }" /></p>
      <menu>
         <li><g:link action="view" params="[file:title]">Cancel</g:link></li>
         <li><g:submitButton name="save" value="Save" /></li>
      </menu>
      <!-- Hidden Inputs -->
      <g:textField name="old" value="${ title }" hidden="hidden"/>
      <g:textField name="created" value="${ created }" hidden="hidden"/>
   </g:form>
</body>
</html>