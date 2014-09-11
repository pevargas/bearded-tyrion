<!DOCTYPE html>
<html>
   <head>
      <meta name="layout" content="main"/>
      <title>Article</title>
   </head>
   <body>
      <header>
         <h1>${title}</h1>
      </header>
      <article>
         <aside>
            <g:link action="index">Home</g:link>
            <g:link action="edit" params="[file:title]">Edit</g:link>
         </aside>
         ${ raw( content ) }
      </article>

   </body>
</html>