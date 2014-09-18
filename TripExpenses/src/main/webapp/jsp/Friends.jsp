<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"
	import="ro.sync.tripexpenses.tables.UserRegistered"
	import="java.util.ArrayList" import="javax.servlet.http.Cookie"
	import="org.hibernate.Query" import="org.hibernate.Session"
	import="ro.sync.tripexpenses.singleton.SingletonSessionFactory"
	import="java.util.*"%>

<div role="main" class="ui-content" data-role="content" id="friendList">
	<div class="content-primary">
		<form>
			<ul class="ui-listview ui-listview-inset ui-corner-all ui-shadow"
				data-role="listview" data-inset="true" id="friendsList"
				data-theme="b">
				
				<li>
				<div data-role="navbar" data-iconpos="top">
                <ul >
                    <li><a href="#" class="ui-btn" data-theme="a"
                        data-icon="plus" id="addButton">Add Friend</a></li>
                        <li> </li>
                        <li><a href="#" class="ui-btn" data-theme="a"
                        data-icon="minus">Remove Friends</a></li>
                </ul>
            </div>
            </li>
				<%
				    String sessionID = "";
				    Cookie[] cookies = request.getCookies();
				    if (cookies != null) {
				        Cookie cookie = null;
				        for (int i = 0; i < cookies.length; i++) {
				            cookie = cookies[i];
				            if (cookie.getName().equals("sid")) {
				                sessionID = cookie.getValue();
				                //begin
				                Collection<UserRegistered> friends = null;
				                Session hiberSession = SingletonSessionFactory
				                        .getNewSession();
				                try {
				                    hiberSession.beginTransaction();
				                    Query query = hiberSession
				                            .createQuery("from UserRegistered where sessionID='"
				                                    + sessionID + "'");
				                    UserRegistered u = (UserRegistered) query
				                            .uniqueResult();
				                    friends = u.getUserFriends();
				                }catch (Exception ex) {
				                    out.println("prieteni");
				                }
				                if (!friends.isEmpty()) {
				                    for (UserRegistered friend : friends) {
				                        String name = friend.getUserName();
				                        String email = friend.getEmailAdress();
				%>
				<li data-name='<%=email%>'><a href="#"><%=name%></a></li>
				<%
				    }
				                }else {
				                    out.println("the friendList is empty");
				                }
				                hiberSession.getTransaction().commit();
				                hiberSession.close();
				                break;
				            }
				        }
				    }
				%>
			</ul>
		</form>

	</div>

</div>
