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
    	$('#newCompanySubmit').submit(function() {
    		var invalid = [];
    		$.each($(this).children('.modal-body').children('input'), function(idx, dom) {
    			var $dom = $(dom);
    			if (!$dom.val()) {
    			  invalid.push($dom.attr('name'));
    			}
    		});
    		if (invalid.length) {
    			alert(invalid.join(', ') + '를 입력해주세요!');
    			return false;
    		} else {
    			return true;
    		}
    	});
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
                  <#if hasCompany??>
                    <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Add Company</button>
                  </#if>
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
                    <span>${company.name}</span>
                    <span>${company.description}</span>
                </a>
            </div>
        <#if company?index % 4 == 3 || company?is_last>
        </div>
        </#if>
      </#list>
        <!-- /.row -->
        <hr>

			<div id="myModal" class="modal fade" role="dialog">
			  <div class="modal-dialog">
			
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <h4 class="modal-title">새 뉴스 제공사</h4>
			      </div>
			      <form id="newCompanySubmit" enctype="application/json" action="${rc.getContextPath()}/company/new" method="post">
				      <div class="modal-body">
				        
				          <label for="name">Name:</label>
	                <input type="text" class="form-control" id="name" name="name">
	                <label for="description">Description:</label>
	                <input type="text" class="form-control" id="description" name="description">
	                <label for="image">Image URL:</label>
	                <input type="url" class="form-control" id="image" name="image">
				      </div>
				      <div class="modal-footer">
				        <input type="submit" class="btn btn-success" value="Submit">
				        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				      </div>
			      </form>
			    </div>
			
			  </div>
			</div>
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
