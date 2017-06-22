db.createUser({ user: 'user', pwd: 'password', roles: [ {role:'readWrite', db:'cloud'} ]});

db.createCollection("cloud");
