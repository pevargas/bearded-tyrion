<!DOCTYPE html>
<html lang="en">
    <head>
        <title><g:layoutTitle default="An example decorator" /></title>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
        <g:layoutHead />
        <r:layoutResources />
    </head>
    <body>

      <header>
        <div class="container">
          <g:link controller="tracker">
            <img src="${resource(dir: 'images', file: 'grails_logo.png')}" id="logo" alt="Grails Logo"/>
          </g:link>
          <h1>Miki</h1>
        </div>
      </header>

        <article class="body">
            <g:if test="${ flash.message }">
                <div class="container">
                    <aside class="alert"><b>${ new Date() }:</b> ${ flash.message }</aside>
                </div>
            </g:if>
            <g:layoutBody />
        </article>

        <r:layoutResources />
    </body>
</html>