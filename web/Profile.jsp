<%-- 
    Document   : Profile
    Created on : Mar 17, 2023, 1:59:39 PM
    Author     : DELL
--%>
<%@page import="entity.Bank"%>
<%@page import="dao.PayMentDAO"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="entity.Account" %>
<%@ page import="dao.AccountDAO" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>CommunityUnity</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/profile.css">
        <link rel="stylesheet" href="css/templatemo-style.css">
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <link rel="stylesheet" href="css/templatemo-style.css">
        <link rel="stylesheet" href="css/fontawesome.min.css" />
        <style>
            .photo-frame {
                border: 2px;
                padding: 10px;
                width: 150px;
                height: 150px;
                text-align: center;
            }

            .photo-frame img {
                max-width: 80%;
                max-height: 80%;
            }






        </style>
    </head>
    <body>

        <%
            String name = ((Account) session.getAttribute("LOGIN_USER")).getUserName();

            Account a = new Account();
            AccountDAO dao = new AccountDAO();

            a = dao.getAccount_BYUSER(name);
            String image = a.getPhoto();


        %>
        <div class="form1">

            <c:set var="p" value=""/>

            <div class="edit-profile">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <h5>MY PROFILE</h5>

                    </div>
                </div>
                <hr>
                <div class="row">

                    <div class="container mt-12">

                        <!-- row -->
                        <div class="row tm-content-row">
                            <div class="tm-block-col tm-col-avatar">
                                <div class="tm-bg-primary-dark tm-block tm-block-avatar" style="color: white;">
                                    <h2 class="tm-block-title">Change Avatar</h2>
                                    <div class="tm-avatar-container">
                                        <div class="photo-frame" style="height: 100%; width:  130%;">
                                            <img src="<%=image%>">
                                            <c:if test="${sessionScope.LOGIN_USER.photo == null  || sessionScope.LOGIN_USER.photo eq ''}">
                                                <img src="./images/uer.png" alt="images/uer.png">
                                            </c:if> 

                                        </div>
                                    </div>



                                </div>
                            </div>
                            <div class="tm-block-col tm-col-account-settings">
                                <div class="tm-bg-primary-dark tm-block tm-block-settings">
                                    <h2 class="tm-block-title">Account Settings</h2>
                                    <form action="" class="tm-signup-form row" style="width: 151%;">


                                        <div class="form-group col-lg-6">
                                            <label for="name">Account Name</label>

                                            <input
                                                id="email"
                                                name="username"
                                                type="text"
                                                placeholder="${sessionScope.LOGIN_USER.userName}" readonly
                                                class="form-control validate" style="background-color: white;"
                                                />


                                        </div>
                                        <div class="form-group col-lg-6">
                                            <label for="SEX">SEX</label>
                                            <input
                                                id="phone"
                                                name="sex"
                                                type="text"
                                                placeholder="<%=a.getSex()%>"
                                                class="form-control validate"  style="background-color: white;"
                                                />
                                        </div>

                                        <div class="form-group col-lg-6">
                                            <label for="email">Account Email</label>
                                            <input
                                                id="email"
                                                name="email"
                                                type="email"
                                                placeholder="<%=a.getEmail()%>"
                                                class="form-control validate"  style="background-color: white;"
                                                />
                                        </div>
                                        <div class="form-group col-lg-6">
                                            <label for="phone">Phone</label>
                                            <input
                                                id="phone"
                                                name="phone"
                                                type="text"
                                                placeholder="<%=a.getPhone()%>"
                                                class="form-control validate"  style="background-color: white;"
                                                />
                                        </div>
                                        <div class="form-group col-lg-6">
                                            <label for="DateBirth">DateBirth</label>
                                            <input
                                                id="phone"
                                                name="dateBirth"
                                                type="text"
                                                placeholder="<%=a.getBirtDay()%>"
                                                class="form-control validate"  style="background-color: white;"
                                                />
                                        </div>

                                        <div class="form-group col-lg-6">
                                            <label for="Address">Address</label>
                                            <input
                                                id="phone"
                                                name="address"
                                                type="text"
                                                placeholder="<%=a.getAddress()%>"
                                                class="form-control validate"  style="background-color: white;"
                                                />

                                        </div>

                                        </table>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    </form>
                    <div>
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@getbootstrap">EDIT</button>
                        <a href="home" class="btn btn-primary" data-bs-whatever="@getbootstrap"> BACK HOME </a>
                        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Update Information</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <form action="ProfileControl" method="post"  enctype="multipart/form-data"> 
                                        <div class="modal-body">

                                            <div class="mb-3">
                                                <label for="image" class="col-form-label">Image: </label>
                                                <input type="file" class="form-control" id="image" name="image">
                                            </div>
                                            <div class="mb-3">
                                                <label for="recipient-name" class="col-form-label">Username: </label>
                                                <input type="text" class="form-control" id="recipient-name" value="${sessionScope.LOGIN_USER.userName}" name="username" readonly>
                                            </div>
                                            <div class="mb-3">
                                                <label for="recipient-name" class="col-form-label">Name</label>
                                                <input type="text" class="form-control" id="recipient-name" value="<%=a.getFullName()%>" name="name">
                                            </div>
                                            <div class="mb-3">
                                                <label for="recipient-name" class="col-form-label">SEX</label>

                                                <select id="recipient-name" class="form-control" name="sex"  placeholder ="<%=a.getSex()%>" style="height: auto;">
                                                    <option value="0">Female</option>
                                                    <option value="1">Male</option>
                                                </select>

                                            </div>



                                            <div class="mb-3">
                                                <label for="recipient-name" class="col-form-label">Phone:</label>
                                                <input type="number" class="form-control" id="recipient-name" value="<%=a.getPhone()%>" name="phone">
                                            </div>

                                            <div class="mb-3">
                                                <label for="message-text" class="col-form-label">Address</label>
                                                <input type="text" class="form-control" id="message-text" value="<%=a.getAddress()%>" name="address">
                                            </div>
                                            <div class="mb-3">
                                                <label for="message-text" class="col-form-label">Date of Birth</label>
                                                <input type="text" class="form-control" id="message-text" value="<%=a.getBirtDay()%>" name="birthDAY">
                                            </div>
                                            <div class="mb-3">
                                                <label for="message-text" class="col-form-label">Email:</label>
                                                <input type="email" class="form-control" id="message-text" value="${sessionScope.LOGIN_USER.email}" name="email">
                                            </div>

                                        </div>
                                        <div class="modal-footer">

                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <input type="text" class="form-control" id="recipient-name" value="${sessionScope.LOGIN_USER.userName}" name="username" hidden="">
                                            <input type="submit" class="btn btn-primary" value="Update"> 
                                        </div>


                                    </form>


                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

    </body>
</html>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
<!-- <script src="profile/"></script> -->