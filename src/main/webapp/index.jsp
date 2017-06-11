<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Peanut Medical Center</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700&amp;subset=latin-ext" rel="stylesheet">

    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id"
          content="174879287253-49bn9ascj5832eil8u652gdbk20bve2g.apps.googleusercontent.com">
</head>
<body id="welcome" data-spy="scroll" data-target=".navbar" data-offset="60">
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#welcome">
                <span class="glyphicon glyphicon-link"></span> PEANUT MEDICINE</a>
        </div>
        <div class="collapse navbar-collapse navHeaderCollapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#about">ABOUT US</a></li>
                <li><a href="#">APPOINTMENTS</a></li>
                <li><a href="survey">NEW PATIENTS</a></li>
                <%--<li><a href="#">PRESCRIPTIONS</a></li>--%>
                <li><a href="#services">SERVICES</a></li>
                <li><a href="#staff">STAFF</a></li>
                <li><a href="#contact">CONTACT</a></li>

                <c:choose>
                <c:when test="${logged}">

                    <li><a href="https://www.google.com/accounts/Logout?continue=https://appengine.google.com/_ah/logout?continue=http://localhost:8080/peanut/logout">SIGN OUT</a></li>

                </c:when>
                    <c:otherwise>
                        <li>
                            <div class="g-signin2" data-onsuccess="onSignIn"></div>
                        </li>
                    </c:otherwise>
                </c:choose>
                <li><a href="admin"><span class="glyphicon glyphicon-text-background"></span></a></li>
            </ul>
        </div>

        <script>
            //google callback. This function will redirect to our login servlet
            function onSignIn(googleUser) {
                var profile = googleUser.getBasicProfile();
                console.log('ID: ' + profile.getId());
                console.log('Name: ' + profile.getName());
                console.log('Image URL: ' + profile.getImageUrl());
                console.log('Email: ' + profile.getEmail());
                console.log('id_token: ' + googleUser.getAuthResponse().id_token);

                //do not post all above info to the server because that is not secure.
                //just send the id_token

                var redirectUrl = '/peanut/login';

//                using jquery to post data dynamically
                var form = $('<form action="' + redirectUrl + '" method="post">' +
                    '<input type="text" name="id_token" value="' +
                    googleUser.getAuthResponse().id_token + '" />' +
                    '</form>');
                $('body').append(form);
                form.submit();
            }
        </script>

    </div>
</nav>

<div class="jumbotron text-center">
    <h1>Welcome to Peanut Medical Center</h1>
    <p>Do It Online!</p>
</div>

<div id="about" class="container-fluid">
    <div class="row">
        <div class="col-sm-8">
            <h2>About Peanut Medical Center</h2>
            <h4></h4>
            <p>At our surgery you will stay under the best care of experienced and respected specialists. We will devote
                to you as much of our time as you need, and we will take care of your health in a comprehensive way. We
                value our patients’ time.</p>
            <p>Patient Access lets you use the online services of your local practice. These may include arranging
                appointments, repeat medication, secure messages, medical record and updating your details...</p>
            <a type="button" class="btn btn-default btn-lg" href="#contact">Contact us</a>
        </div>
        <div class="col-sm-4 text-center">
            <span class="glyphicon glyphicon-link logo"></span>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-4 text-center">
            <span class="glyphicon glyphicon-hand-right logo"></span>
        </div>
        <div class="col-sm-8">
            <h2>Our Values</h2>
            <h4><strong>MISSION:</strong> We offer outstanding medical care services. The standards set by us are more
                than just technologically advanced equipment, specialistic team, and comfortable interiors.</h4>
            <p><strong>VISION:</strong> At our surgery you will stay under the best care of experienced and respected
                specialists. We will devote to you as much of our time as you need, and we will take care of your health
                in a comprehensive way. We value our patients’ time.</p>
        </div>
    </div>
</div>

<div class="container-fluid text-center bg-grey">
    <h2>WIDE SCOPE OF TREATMENT</h2>
    <h4></h4>
    <div class="row text-center">
        <div class="col-sm-4">
            <div class="thumbnail">
                <img src="images/nurse-doctor-surgery.jpg" alt="Nurse Doctor">
                <h3>Medical Consultations</h3>
                <p>We will devote to you as much of our time as you need, and we will take care of your health in a
                    comprehensive way...</p>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="thumbnail">
                <img src="images/doctors-stethoscope.jpg" alt="Doctors stethoscope">
                <h3>Diagnostic</h3>
                <p>Diagnostics is extremely important for the assessment of a patient’s condition and effective
                    therapy...</p>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="thumbnail">
                <img src="images/doctor-needle.jpg" alt="Doctor needle">
                <h3>Vaccinations</h3>
                <p>Vaccines help the body to strengthen its resistance by producing the right kind and number of
                    antibodies...</p>
            </div>
        </div>
    </div>
</div>

<div id="services" class="container-fluid text-center">
    <h2>SERVICES</h2>
    <br>
    <div class="row">
        <div class="col-sm-4">
            <span class="glyphicon glyphicon-calendar logo-small"></span>
            <h4>ONLINE APPOINTMENT SYSTEM</h4>
            <p>If you have registered with us for access to online appointments, you can book a Doctors appointment
                through our online system up to 14 days in advance...</p>
        </div>
        <div class="col-sm-4">
            <span class="glyphicon glyphicon-folder-close logo-small"></span>
            <h4>ONLINE ACCESS TO MEDICAL RECORDS</h4>
            <p>Patient Access lets you use the online services. These may include arranging appointments, repeat
                medication, secure messages, medical record and updating your details.</p>
        </div>
        <div class="col-sm-4">
            <span class="glyphicon glyphicon-phone logo-small"></span>
            <h4>24 HOUR TELEPHONE BOOKING</h4>
            <p>24 hour automated telephone booking system available. To book, cancel or check an appointment please
                call:</p>
            <p><span class="glyphicon glyphicon-phone"></span> 01 234 567 890</p>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-4">
            <span class="glyphicon glyphicon-earphone logo-small"></span>
            <h4>TELEPHONE CONSULTATIONS</h4>
            <p>The surgery offers same day telephone and face-to-face consultations via the minor illness nurse and
                triage doctor...</p>
        </div>
        <div class="col-sm-4">
            <span class="glyphicon glyphicon-home logo-small"></span>
            <h4>HOME VISITS</h4>
            <p>Whilst we encourage our patients to come to the surgery, where we have the proper equipment and
                facilities available, we do appreciate this is not always possible...</p>
        </div>
        <div class="col-sm-4">
            <span class="glyphicon glyphicon-list-alt logo-small"></span>
            <h4>REPEAT PRESCRIPTIONS</h4>
            <p>Medication that your doctor has authorised on your file which can be supplied for a certain period of
                time on a regular basis without having to see your doctor each time...</p>
        </div>
    </div>
</div>

<div id="staff" class="container-fluid text-center bg-grey">
    <h2>OUR TEAM</h2>
    <h4>Experienced doctors will take care of your health and well-being</h4>
    <br>
    <div class="row text-center">
        <div class="col-sm-3">
            <div class="thumbnail"><a href="#">
                <span class="glyphicon glyphicon-user logo"></span>
                <hr>
                <h3>Piotr Nowak, MD</h3>
                <p>Internist</p></a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="thumbnail"><a href="#">
                <span class="glyphicon glyphicon-user logo"></span>
                <hr>
                <h3>Tomasz Malinowski, MD</h3>
                <p>Laryngologist</p></a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="thumbnail"><a href="#">
                <span class="glyphicon glyphicon-user logo"></span>
                <hr>
                <h3>Anna Maria, MD</h3>
                <p>Urologist</p></a>
            </div>
        </div>
        <div class="col-sm-3">
            <div class="thumbnail"><a href="#">
                <span class="glyphicon glyphicon-user logo"></span>
                <hr>
                <h3>Jan Kowalski, MD</h3>
                <p>Dentist</p></a>
            </div>
        </div>
    </div>
</div>

<div id="contact" class="container-fluid">
    <h2 class="text-center">WRITE TO US</h2>
    <div class="row">
        <div class="col-sm-5">
            <p>Contact us and we'll get back to you within 24 hours.</p>
            <p><span class="glyphicon glyphicon-map-marker"></span> Gdansk, PL</p>
            <p><span class="glyphicon glyphicon-phone"></span> 01 234 567 890</p>
            <p><span class="glyphicon glyphicon-envelope"></span> contact@peanutmedicalcenter.com</p>
        </div>
        <div class="col-sm-7">
            <div class="row">
                <div class="col-sm-6 form-group">
                    <input class="form-control" id="name" name="name" placeholder="Name" type="text" required>
                </div>
                <div class="col-sm-6 form-group">
                    <input class="form-control" id="email" name="email" placeholder="Email" type="email" required>
                </div>
            </div>
            <textarea class="form-control" id="comments" name="comments" placeholder="Comment" rows="5"></textarea><br>
            <div class="row">
                <div class="col-sm-12 form-group">
                    <button class="btn btn-default btn-lg pull-right" type="submit">Send</button>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="container-fluid text-center bg-grey">
    <a href="#welcome" title="To Top">
        <span class="glyphicon glyphicon-chevron-up"></span>
    </a>
    <p>Copyright @ PeanutMedicine for infoShare Academy 2017</p>
</footer>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
</body>
</html>