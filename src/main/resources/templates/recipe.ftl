<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
  <link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css">
  <link rel="stylesheet" type="text/css" href="${rc.getContextPath()}/css/4-col-portfolio.css">
  
  <title>Management Recipe</title>
 
  <script src="${rc.getContextPath()}/webjars/jquery/3.1.0/jquery.min.js"></script>
  <script src="${rc.getContextPath()}/webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
  
  <script>
    $( document ).ready( function() {
    	
    }) 
  </script>
</head>
<body>
  <#assign hasRecipe = recipes?size gt 0>
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
                <button id="newCompany" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">Add Company</button>
                </h1>
            </div>
        </div>
        <!-- /.row -->
        <hr>

			<div id="myModal" class="modal fade" role="dialog">
			  <div class="modal-dialog">
			
			    <!-- Modal content-->
			    <div class="modal-content">
			      <div class="modal-header">
			        <h4 class="modal-title">레시피</h4>
			      </div>
			      <form id="companySubmit" method="post">
				      <div id="modal_body" class="modal-body">
				        
				          <label for="modal_company_name">Name:</label>
	                <input type="text" class="form-control" id="modal_company_name" name="name">
	                <label for="modal_company_description">Description:</label>
	                <input type="text" class="form-control" id="modal_company_description" name="description">
	                <label for="modal_company_image">Image URL:</label>
	                <input type="url" class="form-control" id="modal_company_image" name="image">
				      </div>
				      <div class="modal-footer">
				        <input type="submit" class="btn btn-success" value="Submit">
				        <button type="button" class="btn btn-default modal_close" data-dismiss="modal">Close</button>
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
