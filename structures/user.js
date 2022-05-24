User = class {
    constructor(username=null, discord=null, youtube=null, github=null){
        this.username = username;
        this.discordHandle = discord;
        this.youtubeHandle = youtube;
        this.githubHandle = github;
    }

    find(db, id){
        return new Promise((resolve)=>{
            db.db.query('SELECT id, username, discord, youtube, github FROM User WHERE id="'+id+'"', function (err, result, fields) {
                let user = {
                    username : result[0].username,
                    discordHandle : result[0].discord,
                    youtubeHandle : result[0].youtube,
                    githubHandle : result[0].github
                }
                resolve(user);
            });
        });
    }
}

module.exports = User;