<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/registration.js"></script>
    <link type="text/css" href="${pageContext.request.contextPath}/css/registration.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/css/button.css" rel="stylesheet">
</head>
<body>
<section>
    <div class="container">
        <p>Registration form</p>
        <div class="row">
            <div class="col-md-6">
                <form action="" method="post" id="fileForm" role="form">
                    <input type="hidden" name="command" value="registration" />
                    <fieldset><legend class="text-center">Valid information is required to register. <span class="req"><small> required *</small></span></legend>

                        <div class="form-group">
                            <label for="username"><span class="req">* </span> User name:  <small>This will be your login user name</small> </label>
                            <input class="form-control" type="text" name="reg-login" id = "username" minlength="4" maxlength="16" onkeyup = "Validate(this)" placeholder="minimum 4 letters" required />
                            ${errorLoginExists}
                        </div>

                        <div class="form-group">
                            <label for="pass1"><span class="req">* </span> Password: </label>
                            <input required name="reg-password" type="password" class="form-control inputpass" minlength="4" maxlength="16"  id="pass1" /> </p>

                            <label for="pass2"><span class="req">* </span> Password Confirm: </label>
                            <input required name="reg-password" type="password" class="form-control inputpass" minlength="4" maxlength="16" placeholder="Enter again to validate"  id="pass2" onkeyup="checkPass(); return false;" />
                            <span id="confirmMessage" class="confirmMessage"></span>
                        </div>

                        <div class="form-group">
                            <label for="email"><span class="req">* </span> Email Address: </label>
                            <input class="form-control" required type="text" name="reg-email" id = "email"  onchange="email_validate(this.value);" />
                            <div class="status" id="status"></div>
                            <br>
                            ${errorEmailExists}
                        </div>

                        <div class="form-group">
                            <label><span class="req">* </span> Gender</label>
                            <input type="radio" name="reg-sex" id="sex-m" value="true" checked>
                            <label for="sex-m">Male</label>
                            <input type="radio" name="reg-sex" id="sex-f" value="false">
                            <label for="sex-f">Female</label>
                        </div>

                        <div class="form-group">
                            <label><span class="req">* </span> Date of birth: </label>
                            <script>document.getElementById("birth").max = "2018-05-05";</script>
                            <input required type="date" id="birth" name="reg-birth" min="1900-01-01" max="2018-05-01"> <%--todo dynamic change of max birthdate--%>
                        </div>
                        <div class="form-group">
                            <input class="btn btn-success" type="submit" name="submit_reg" value="Register">
                        </div>
                    </fieldset>
                </form><!-- ends register form -->
            </div><!-- ends col-6 -->
        </div>
    </div>
</section>
</body>
</html>