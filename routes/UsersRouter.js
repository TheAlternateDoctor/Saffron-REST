var User = require("../structures/user.js");
var Mysql = require("../database/mysql.js");
var express = require('express');

class UsersRouter {

	constructor(authentication) {
		this.router = express.Router();
		const db = new Mysql();

		/* GET users listing. */
		this.router.get('/',authentication.protect('default-roles-saffron'), function (req, res, next) {
			let users = User.findAll(db).then((result) => {
				res.send(JSON.stringify(result));
			}).catch((error) => {
				if (error === "result") {
					res.statusCode = 404;
					res.send('{"note":"No users, wtf"}');
				} else {
					res.statusCode = 500;
					res.send('{"note":"MySQL error.",' +
						'"error":' + JSON.stringify(error) + '}');
				}
			});
		});

		/* POST user, creating it in the process. Will require Keycloak id*/
		this.router.post('/', authentication.protect(), function (req, res, next) {
			res.send('respond with a specific code and make it')
		});

		/* GET specific user */
		this.router.get('/:uid', function (req, res, next) {
			let id = req.params.uid;
			let user = User.find(db, id).then((result) => {
				res.send(JSON.stringify(result));
			}).catch((error) => {
				if (error === "result") {
					res.statusCode = 404;
					res.send('{"note":"No user with ID ' + id + '"}');
				} else {
					res.statusCode = 500;
					res.send('{"note":"MySQL error.",' +
						'"error":' + JSON.stringify(error) + '}');
				}
			});
		});

		/* GET mods made by a user*/
		this.router.get('/:uid/mods', function (req, res, next) {
			let id = req.params.uid;
			res.send('get mods by user ' + id);
		});

	}

	getRoutes(){
		return this.router;
	}

}

module.exports = UsersRouter;
