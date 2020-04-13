var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});
router.get('/index', function(req, res, next) {
    res.render('index', { title: 'Express' });
});
router.get('/addStaff', function(req, res, next) {
    res.render('addStaff', { title: 'Express' });
});
router.get('/shenhe', function(req, res, next) {
    res.render('shenhe', { title: 'Express' });
});
router.get('/charts', function(req, res, next) {
  res.render('charts', { title: 'Express' });
});
router.get('/forms', function(req, res, next) {
  res.render('forms', { title: 'Express' });
});
router.get('/login', function(req, res, next) {
  res.render('login', { title: 'Express' });
});
router.get('/tables', function(req, res, next) {
  res.render('tables', { title: 'Express' });
});
router.get('/test', function(req, res, next) {
  res.render('test', { title: 'Express' });
});
module.exports = router;
