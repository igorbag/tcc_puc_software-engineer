'use strict';

const router = require('express').Router()
const ctrl = require('../controllers/student-controller');

router.get('/', (req, res) => {
    res.send('Bem vindo - API NodeJs');
});
router.post('/auth', ctrl.auth);
router.get('/student', ctrl.getAll);
router.get('/student/:id', ctrl.getById);
router.post('/student', ctrl.create);
router.put('/student/:id', ctrl.update);
router.delete('/student/:id', ctrl.remove);

module.exports = router;
