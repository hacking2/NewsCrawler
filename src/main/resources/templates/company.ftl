<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
  <link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/css/4-col-portfolio.css">
  
  <title>Management Company</title>
 
  <script src="${rc.getContextPath()}/webjars/jquery/3.1.0/jquery.min.js"></script>
  <script src="${rc.getContextPath()}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
  
  <script>
    $( document ).ready( function() {
    
    }) 
  </script>
</head>
<body>
  <#assign hasCompany = companies?size gt 0>
<!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">News Crawler Management</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>Company</li>
                    <li>
                        <a href="${rc.getContextPath()}/recipe/all">Crawl Recipe</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    <div class="container">
        <!-- Page Heading -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">News Company List
                    <small></small>
                </h1>
            </div>
        </div>
        <!-- /.row -->

        <!-- Projects Row -->
      <#list companies as company>
        <#if company?index % 4 == 0>
        <div class="row">
        </#if>
            <div class="col-md-3 portfolio-item">
                <a href="${rc.getContextPath()}/recipe/${company.id}">
                    <img class="img-responsive" src="<#if company.image??>${company.image}<#else>none</#if>" alt="">
                </a>
            </div>
        <#if company?index % 4 == 3 || company?is_last>
        </div>
        </#if>
      </#list>
        <!-- /.row -->
        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->
</body>
</html>
