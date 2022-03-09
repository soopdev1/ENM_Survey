<%@page import="soop.engine.Action"%>
<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Survey - MCN</title>
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="css/googlefontcss.css" rel="stylesheet">
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

        <link rel="shortcut icon" href="favicon.ico" />    
    </head>

    <body class="bg-gradient-primary">

        <div class="container">
            <!-- Button trigger modal -->
            <div class="modal fade"  tabindex="-1" aria-hidden="true">
                <button id="modalerrorbutton" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">
                    Launch demo modal
                </button>
            </div>
            <!-- Modal -->
            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title text-danger" id="exampleModalLabel">ERRORE</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            I DATI DI ACCESSO INSERITI NON SONO CORRETTI. CONTROLLARE LA MAIL RICEVUTA.
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Outer Row -->
            <div class="row justify-content-center">
                <%
                    String play = Action.getRequestValue(request, "play");
                    String utente = Action.getRequestValue(request, "us");
                    //utente = Action.generaidsurvey("N", "I", "963");

                    String[] dati = Action.getCF(utente);
                %>


                <%if (!play.equals("YRC")) {%>
                <div class="col-xl-10 col-lg-12 col-md-9">
                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <!-- Nested Row within Card Body -->
                            <div class="row">
                                <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                                <div class="col-lg-6">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h1 class="h4 text-gray-900 mb-4">Survey <br> YES I START UP 2021/2022</h1>
                                        </div>
                                        <hr>
                                        <form class="login100-form validate-form" method="post" action="Operations?type=startsurvey">
                                            <input type="hidden" name="piattaforma" value="<%=dati[0]%>" />
                                            <input type="hidden" name="tiposurvey" value="<%=dati[1]%>" />
                                            <input type="hidden" name="iduser" value="<%=dati[2]%>" />

                                            <div class="input-group mb-3">
                                                \
                                                <div class="input-group-prepend">
                                                    <span class="input-group-text" id="basic-addon1"><i class="fa fa-user"></i></span>
                                                </div>
                                                <input type="text" name="cf" class="form-control required" placeholder="CODICE FISCALE" aria-label="CODICE FISCALE" 
                                                       aria-describedby="basic-addon1" readonly value="<%=dati[3]%>" />
                                            </div>
                                            <small id="emailHelp" class="form-text text-danger">N.B. Se il codice fiscale visualizzato non corrisponde al proprio contattare il supporto tecnico.</small>
                                            <hr>
                                            <div class="input-group mb-3">
                                                <button class="btn btn-primary btn-user btn-block">
                                                    <i class="fa fa-play-circle"></i> START
                                                </button>
                                            </div>
                                        </form>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <%} else {%>
                <div class="col-xl-10 col-lg-12 col-md-9">
                    <div class="card o-hidden border-0 shadow-lg my-5">
                        <div class="card-body p-0">
                            <div class="embed-responsive embed-responsive-4by3">
                                <iframe class="embed-responsive-item" src="https://stage-surveyb.bss-lab.it/s/b3b3f0f7-e17c-4339-adf3-85ea4a1777b2"></iframe>
                            </div>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>

</html>
