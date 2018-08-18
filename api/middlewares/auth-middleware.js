'use strict';

const AUTHORIZATION_MODEL = 'Basic';
const USER_PASS_SPLIT = ':';
const Student = require('../models/student-model')

/**
 * Modulo responsavel por gerenciar um middleware de autenticacao 
 * Basic
 * 
 * @param {Request} req 
 * @param {Response} res 
 * @param {Proximo} next 
 */
module.exports = (req, res, next) => {
  if(!req.headers.authorization) {
    return res.status(403).send();
  }

  let _b64UserName = req.headers.authorization.split(AUTHORIZATION_MODEL).pop().trim();
  let _arrayUserName = Buffer.from(_b64UserName, 'base64').toString('utf-8').split(USER_PASS_SPLIT);

  Student.authenticate({ email: _arrayUserName[0], senha: _arrayUserName[1] })
    .then(student => {
      req.student = student;
    
      return next();
    })
    .catch(err => {
      return res.status(403).send(err);
    });
};
