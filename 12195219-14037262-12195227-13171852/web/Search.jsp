<%-- 
    Document   : index
    Created on : Apr 16, 2015, 5:27:44 AM
    Author:
    Nader    12195219
    Mamnoon  14037262
    Khaled   12195227
    Yaser    13171852
    This is searcg result page, it show you the list of the search product
--%>

<%@page import="EntityClass.Product"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
    <head>
        <title>Online Shopping Cart</title>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
        <link rel="shortcut icon" href="css/images/favicon.ico" />
        <link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
        <link rel="stylesheet" href="css/jquery.jscrollpane.css" type="text/css" media="all" />
        <link href="css/Test.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-1.7.min.js" type="text/javascript"></script>
        <script src="js/DD_belatedPNG-min.js" type="text/javascript"></script>
        <script src="js/jquery.jscrollpane.min.js" type="text/javascript"></script>
        <script src="js/jquery.jcarousel.js" type="text/javascript"></script>
        <script src="js/functions.js" type="text/javascript"></script>
    </head>
    <body>
        <!--This is the Form which is calling After passing User name and Password. It is post the data to Admin servlet -->
        <form name="myform" action="Search" method="Post">
            <!-- CSS is using for designing the UI -->
            <div id="wrapper">
                <div id="wrapper-bottom">
                    <div class="shell">
                        <!-- Header -->
                        <div id="header">
                            <!-- Logo -->
                            <h1 id="logo"><a title="Home" href="#">accessories store</a></h1>
                            <p class="shopping-cart">
                                <% String myname1 = (String) session.getAttribute("username");
                                    if (myname1 != null) {
                                        out.println(" <span> Welcome </span>" + myname1 + "<span>as a </span>" + request.getSession().getAttribute("urole"));

                                    } else {

                                        out.println("Your Shopping Cart");

                                    }
                                %>
                                &nbsp;&nbsp;<a class="cart" href="MyCart"></a>
                                <!-- Search -->
                                <div class="search-expand"></div>
                                <div id="search">
                                    <div class="retract-button">
                                        <p>retract</p>
                                    </div>

                                </div>
                                <!-- END Search -->
                                <div class="cl"></div>
                                <!-- Navigation -->
                                <div id="navigation">
                                    <ul>
                                        <li><a href="Servlet" class="current">Home</a></li>
                                        <li><a href="Searchdata.jsp">Search</a></li>
                                        <!-- Getting the Username from the Sessions and validating -->
                                        <% String myname = (String) session.getAttribute("username");
                                            if (myname != null) {
                                                out.println("<li><a href=\"Logout\" >Logout</a></li>");

                                            } else {

                                                out.println("<li><a href=\"Login.jsp\" >Login</a></li>");

                                            }
                                        %>
                                    </ul>						
                                </div>	
                                <div class="cl"></div>				
                                <!-- END Navigation -->
                                <div class="cl"></div>
                        </div>
                        <!-- END Header -->
                        <!-- Main -->
                        <div id="main">
                            <!-- Slider -->
                            <div class="slider-holder">
                                <div class="promo">
                                    <img src="css/images/promo.jpg" alt="Big Sale This Week -30% OFF!" />
                                </div>						
                                <div id="slider">
                                    <ul>
                                        <li>
                                            <img src="css/images/slide1.jpg" alt="colour pencils" title="" />
                                            <div class="caption">
                                                <h2>Big Sale !!!</h2>
                                                <div class="text"><p>Sale is On Hurry.</p></div>
                                                <div class="buy-now">
                                                    <p>BUY NOW</p>
                                                    <p class="price">$<span>0.99</span></p>
                                                </div>
                                                <div class="cl"></div>
                                                <p><strike>$1.29</strike></p>
                                            </div>
                                        </li>
                                        <li>
                                            <img src="css/images/slide2.jpg" alt="pencils"  title=""/>
                                            <div class="caption">
                                                <h2>Deal of the Day</h2>
                                                <div class="text">
                                                    <p>Amazing prices for your need.</p></div>
                                                <div class="buy-now">
                                                    <p>BUY NOW</p>
                                                    <p class="price">$<span>0.99</span></p>
                                                </div>
                                                <div class="cl"></div>
                                                <p><strike>$1.29</strike></p>
                                            </div>
                                        </li>
                                        <li>
                                            <img src="css/images/slide1.jpg" alt="pencils" />
                                            <div class="caption">
                                                <h2>Deals Week</h2>
                                                <div class="text">
                                                    <p>Offer only for Limited Period.</p>
                                                </div>
                                                <div class="buy-now">
                                                    <p>BUY NOW</p>
                                                    <p class="price">$<span>0.99</span></p>
                                                </div>
                                                <div class="cl"></div>
                                                <p><strike>$1.29</strike></p>
                                            </div>
                                        </li>

                                    </ul>																		
                                </div>						

                            </div>	
                            <!-- END Slider -->
                            <!-- Content -->
                            <div id="content">
                                <table >
                                    <tr>
                                        <td class="chkoutbtn">
                                            <a href="Servlet">Back</a>
                                        </td>
                                    </tr>
                                </table>
                                <div class="products-holder">
                                    <div class="top"></div>
                                    <div class="middle">													
                                        <div class="cl"></div>
                                        <div class="searchdiv">
                                            <h1>Welcome to Search Result Page</h1></br>

                                        </div>

                                        <!-- Getting the Product List from the DB -->
                                        <% List<Product> lp = (List<Product>) request.getAttribute("listProduct");%>
                                        <%
                                            if (lp.isEmpty()) {
                                                out.println("There is no matching result avaiable");
                                                out.println("Go Back and Please search again");

                                            } else {
                                        %>
                                        <% for (Product pdt : lp) {%>
                                        <div class="product">																										
                                            <a href="#"><img src="Book/<%out.println(pdt.getProductImage());%>" alt="image" /></a>
                                            <div class="buy_now_button"><a href="#">Buy Now</a></div>
                                            <div class="desc">
                                                <p class="name"><%out.println(pdt.getProductName());%></p>
                                                <p>Quantity: <span> <%out.println(pdt.getProductQuantity());%></span></p>
                                                <p>Item code: <span><%out.println(pdt.getProductCode());%></span></p>
                                            </div>	
                                            <div class="price-box">
                                                <p>€<span class="price"> <%out.println(pdt.getProductPrice());%></span></p>
                                            </div>
                                            <div class="cl"></div>	
                                        </div>
                                        <%}%>
                                        <%}%>

                                        <div class="cl"></div>
                                    </div>
                                    <div class="bottom"></div>									
                                </div>
                                <!-- END Featured Products -->
                            </div>
                            <!-- END Content -->
                        </div>
                        <!-- END Main -->
                    </div>
                </div>
                <div id="footer-push"></div>
            </div>
            <!-- END Wrapper -->
            <!-- Footer -->
            <div id="footer">
                <div class="shell">
                    <a class="footer-logo" href="#" title="Home"><img src="css/images/logo-footer.png" alt="Logo" /></a>
                    <p id="bottom-nav"><a title="Home" href="#">Home</a><a title="Blog" href="#">Blog</a><a title="About Us" href="#">About Us</a></p>
                    <div class="cl"></div>
                </div>
            </div>
            <!-- END Footer -->
    </body>
</html>