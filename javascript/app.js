const createError = require('http-errors');
const express = require('express');
const path = require('path');
const cookieParser = require('cookie-parser');
const logger = require('morgan');
const session = require("express-session");
const Keycloak = require("keycloak-connect");


//No auth, can act very simply
const indexRouter = require('./routes/index');

//Has auth, so we need to make it a class
const UsersRouter = require('./routes/UsersRouter');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));

const memoryStore = new session.MemoryStore();
const auth = new Keycloak({store: this.memoryStore});
app.use(auth.middleware());

const usersRouter = new UsersRouter(auth);

app.use('/', indexRouter);
app.use('/users', usersRouter.getRoutes());


// catch 404 and forward to error handler
app.use(function(req, res, next) {
  next(createError(404));
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
