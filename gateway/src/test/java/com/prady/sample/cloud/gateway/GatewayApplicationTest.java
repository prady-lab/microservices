/**
 *
 */

package com.prady.sample.cloud.gateway;

import java.io.IOException;
import java.util.List;

import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.prady.sample.cloud.common.dto.user.RoleDTO;
import com.prady.sample.cloud.common.dto.user.UserAccountDTO;
import com.prady.sample.cloud.gateway.helper.RoleHelper;
import com.prady.sample.cloud.gateway.helper.UserAccountHelper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * @author Prady
 */
public class GatewayApplicationTest extends AbstractTestNGSpringContextTests {

    private static final String DEFAULT_URI = "http://localhost";
    private static final int ENDPOINT_PORT = 4000;
    private static final long TEST_TIMEOUT = 30000;

    private static final String USERS_PATH = "users";
    private static final String ROLES_PATH = "roles";

    private final ObjectMapper mapper = new ObjectMapper();
    private final UserAccountHelper userHelper = new UserAccountHelper();
    private final RoleHelper roleHelper = new RoleHelper();

    @BeforeTest
    public void setUpRestAssured() {
        RestAssured.reset();
        RestAssured.port = ENDPOINT_PORT;
        RestAssured.baseURI = System.getProperty("url", DEFAULT_URI);
    }

    /**
     * @param theResponseJson
     * @param constructCollectionType
     * @return
     */
    private <T> T toObject(String theResponseJson, CollectionType constructCollectionType) {
        try {
            return mapper.readValue(theResponseJson, constructCollectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testCreateUser() {
        UserAccountDTO user = userHelper.populateUserAccountDTO();
        final Response theResponse = RestAssured.given().body(user).contentType("application/json").accept("application/json").when()
                .post(USERS_PATH);
        theResponse.then().statusCode(201).contentType(ContentType.JSON);
        testGetUsers();
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testGetUsers() {
        final Response theResponse = RestAssured.given().contentType("application/json").accept("application/json").when().get(USERS_PATH);
        final String theResponseJson = theResponse.prettyPrint();
        theResponse.then().statusCode(200).contentType(ContentType.JSON);
        List<UserAccountDTO> users = toObject(theResponseJson,
                mapper.getTypeFactory().constructCollectionType(List.class, UserAccountDTO.class));
        userHelper.updateUserList(users);

        if (userHelper.isUserEmpty()) {
            testCreateUser();
        }
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testGetUser() {
        if (userHelper.isUserEmpty()) {
            testGetUsers();
        }
        String userId = userHelper.randomUserId();
        final Response theResponse = RestAssured.given().contentType("application/json").accept("application/json").when()
                .get(USERS_PATH + "/{userId}", userId);
        theResponse.then().statusCode(200).contentType(ContentType.JSON);
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testDeleteUser() {
        if (userHelper.isUserEmpty()) {
            testGetUsers();
        }
        String userId = userHelper.randomUserId();
        final Response theResponse = RestAssured.given().contentType("application/json").accept("application/json").when()
                .delete(USERS_PATH + "/{userId}", userId);
        theResponse.then().statusCode(200).contentType(ContentType.JSON);
        userHelper.removeUser(userId);
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testUpdateUser() {
        if (userHelper.isUserEmpty()) {
            testGetUsers();
        }
        UserAccountDTO user = userHelper.randomUser();
        user.setLastName(user.getLastName() + " Updated");
        final Response theResponse = RestAssured.given().body(user).contentType("application/json").accept("application/json").when()
                .put(USERS_PATH + "/{userId}", user.getId());
        theResponse.then().statusCode(200).contentType(ContentType.JSON);
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testCreateUserLoginNameFailed() {
        UserAccountDTO user = userHelper.populateUserAccountDTO();
        user.setUserLoginName(null);
        final Response theResponse = RestAssured.given().body(user).contentType("application/json").accept("application/json").when()
                .post(USERS_PATH);
        theResponse.then().statusCode(400).contentType(ContentType.JSON);
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testCreateUserFirstNameFailed() {
        UserAccountDTO user = userHelper.populateUserAccountDTO();
        user.setFirstName(null);
        final Response theResponse = RestAssured.given().body(user).contentType("application/json").accept("application/json").when()
                .post(USERS_PATH);
        theResponse.then().statusCode(400).contentType(ContentType.JSON);
    }

    /*** ROLE ***/

    @Test(timeOut = TEST_TIMEOUT)
    public void testCreateRole() {
        RoleDTO role = roleHelper.populateRoleDTO();
        final Response theResponse = RestAssured.given().body(role).contentType("application/json").accept("application/json").when()
                .post(ROLES_PATH);
        theResponse.then().statusCode(200).contentType(ContentType.JSON);
        testGetRoles();
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testGetRoles() {
        final Response theResponse = RestAssured.given().contentType("application/json").accept("application/json").when().get(ROLES_PATH);
        final String theResponseJson = theResponse.prettyPrint();
        theResponse.then().statusCode(200).contentType(ContentType.JSON);
        List<RoleDTO> roles = toObject(theResponseJson, mapper.getTypeFactory().constructCollectionType(List.class, RoleDTO.class));
        roleHelper.updateRoleList(roles);

        if (roleHelper.isRoleEmpty()) {
            testCreateRole();
        }
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testGetRole() {
        if (roleHelper.isRoleEmpty()) {
            testGetRoles();
        }
        String roleId = roleHelper.randomRoleId();
        final Response theResponse = RestAssured.given().contentType("application/json").accept("application/json").when()
                .get(ROLES_PATH + "/{roleId}", roleId);
        theResponse.then().statusCode(200).contentType(ContentType.JSON);
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testDeleteRole() {
        if (roleHelper.isRoleEmpty()) {
            testGetRoles();
        }
        String roleId = roleHelper.randomRoleId();
        final Response theResponse = RestAssured.given().contentType("application/json").accept("application/json").when()
                .delete(ROLES_PATH + "/{roleId}", roleId);
        theResponse.then().statusCode(200).contentType(ContentType.JSON);
        roleHelper.removeRole(roleId);
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testUpdateRole() {
        if (roleHelper.isRoleEmpty()) {
            testGetRoles();
        }
        RoleDTO role = roleHelper.randomRole();
        role.setRoleDescription(role.getRoleDescription() + " Updated");
        final Response theResponse = RestAssured.given().body(role).contentType("application/json").accept("application/json").when()
                .put(ROLES_PATH + "/{roleId}", role.getId());
        theResponse.then().statusCode(200).contentType(ContentType.JSON);
    }

    @Test(timeOut = TEST_TIMEOUT)
    public void testCreateRoleFailed() {
        RoleDTO role = roleHelper.populateRoleDTO();
        role.setRoleName(null);
        final Response theResponse = RestAssured.given().body(role).contentType("application/json").accept("application/json").when()
                .post(ROLES_PATH);
        theResponse.then().statusCode(400).contentType(ContentType.JSON);
        testGetRoles();
    }

}
