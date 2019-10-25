<#import "/spring.ftl" as spring/>
<html>
<head>
    <title><@spring.message "title.result"/></title>
    <#include "/WEB-INF/views/static/template.ftl">
</head>
<body>
<#include "/WEB-INF/views/static/header_main.ftl">
<div class="wrapperResult">
    <div class="wrapperLoginInner">
        <h1>Product list</h1>

        <table class="uui-table stripe">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Calories</th>
                <th>Proteins</th>
                <th>Fats</th>
                <th>Carbs</th>
            </tr>
            </thead>
            <tbody>
                <#list listProducts as product>
                    <tr>
                        <td width="20px">${product.id}</td>
                        <td width="100px">${product.name}</td>
                        <td width=100px">${product.calories}</td>
                        <td width="100px">${product.proteins}</td>
                        <td width="80px">${product.fats}</td>
                        <td width="60px">${product.carbs}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
        ${wrongData}
        <br/>
        <h4 class="h3login"><a class="registration" href='/back'>
                <@spring.message "label.backmain" /></a>
        </h4>
    </div>
</div>
</body>
</html>
