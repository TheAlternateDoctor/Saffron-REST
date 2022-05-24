const mysql = require('mysql');
const conf = require('./db.json');

let Mysql = class {
    constructor(){
        this.db = mysql.createConnection({
            host:       conf.host,
            user:       conf.username,
            password:   conf.password,
            database:   conf.database
        });
        this.db.connect(function(err){
            if(err) throw err;
            console.log("Connected!");
        })
    }
}
module.exports = Mysql;