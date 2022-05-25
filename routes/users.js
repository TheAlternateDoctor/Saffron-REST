var User = require("../structures/user.js");
var Mysql = require("../database/mysql.js");

var express = require('express');
var router = express.Router();
let db = new Mysql();

/* GET users listing. */
router.get('/', function (req, res, next) {
  let users = User.findAll(db).then((result)=>{
    res.send(JSON.stringify(result));
  }).catch((error)=>{
    if (error === "result") {
      res.statusCode = 404;
      res.send('{"note":"No users, wtf"}');
    } else {
      res.statusCode = 500;
      res.send('{"note":"MySQL error.",' +
        '"error":'+JSON.stringify(error) + '}');
    }
  });
});

router.post('/', function (req, res, next) {
  res.send('respond with a specific code and make it')
});

router.get('/:uid', function (req, res, next) {
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
        '"error":'+JSON.stringify(error) + '}');
    }
  });
});

router.get('/:uid/mods', function (req, res, next) {
  let id = req.params.uid;
  res.send('get mods by user ' + id);
});

module.exports = router;
