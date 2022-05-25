User = class {
    constructor(username=null, discord=null, youtube=null, github=null){
        this.username = username;
        this.discordHandle = discord;
        this.youtubeHandle = youtube;
        this.githubHandle = github;
    }

    static findAll(db){
        return new Promise((resolve, reject)=>{
            db.db.query('SELECT id, username, discord, youtube, github FROM User', function (err, result, fields) {
                if(err){
                    reject(err);
                } else if(result.length === 0){
                    reject("result");
                } else{
                    let users = new Array();
                    result.forEach(element => {
                        users.push({
                            username : element.username,
                            discordHandle : element.discord,
                            youtubeHandle : element.youtube,
                            githubHandle : element.github
                        })
                    });
                    resolve(users);
                }
            });
        });
    }

    static find(db, id){
        return new Promise((resolve, reject)=>{
            db.db.query('SELECT id, username, discord, youtube, github FROM User WHERE id="'+id+'"', function (err, result, fields) {
                if(err){
                    reject(err);
                } else if(result.length === 0){
                    reject("result");
                } else{
                    let user = {
                        username : result[0].username,
                        discordHandle : result[0].discord,
                        youtubeHandle : result[0].youtube,
                        githubHandle : result[0].github
                    }
                    resolve(user);
                }
            });
        });
    }
}

module.exports = User;