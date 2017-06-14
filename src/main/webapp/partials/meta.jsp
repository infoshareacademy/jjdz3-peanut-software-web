<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<meta charset="UTF-8">
<title>Peanut Medical Center</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="css/main.css">
<link href="https://fonts.googleapis.com/css?family=Lato:400,700&amp;subset=latin-ext" rel="stylesheet">

<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
<script src="https://apis.google.com/js/api:client.js"></script>
<script>
    var googleUser = {};
    var startApp = function() {
        gapi.load('auth2', function(){
            // Retrieve the singleton for the GoogleAuth library and set up the client.
            auth2 = gapi.auth2.init({
                client_id: '174879287253-49bn9ascj5832eil8u652gdbk20bve2g.apps.googleusercontent.com',
                cookiepolicy: 'single_host_origin',
                // Request scopes in addition to 'profile' and 'email'
                //scope: 'additional_scope'
            });
            attachSignin(document.getElementById('customBtn'));
        });
    };

    function attachSignin(element) {
        console.log(element.id);
        auth2.attachClickHandler(element, {},
            function(googleUser) {
                document.getElementById('name').innerText = "Signed in: " +
                    googleUser.getBasicProfile().getName();
                var profile = googleUser.getBasicProfile();

                var redirectUrl = "${pageContext.request.contextPath}/login";
                var form = $('<form action="' + redirectUrl + '" method="post">' +
                    '<input type="text" name="id_token" value="' + googleUser.getAuthResponse().id_token + '" />' +
                    '<input type="text" name="name" value="' + profile.getGivenName() + '" />' +
                    '<input type="text" name="surname" value="' + profile.getFamilyName() + '" />' +
                    '<input type="text" name="imageUrl" value="' + profile.getImageUrl() + '" />' +
                    '<input type="text" name="email" value="' + profile.getEmail() + '" />' +
                    '</form>');
                $('body').append(form);
                form.submit();

            }, function(error) {
                alert(JSON.stringify(error, undefined, 2));
            });
    }
</script>
