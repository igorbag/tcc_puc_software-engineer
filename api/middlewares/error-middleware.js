'use strict';

const messages = require('../i18n/message-pt');
/**
 * Modulo responsavel por gerenciar as mensagens do projeto
 * 
 * @param {Erro} err 
 * @param {Request} req 
 * @param {Response} res 
 * @param {Proximo} next 
 */
module.exports = (err, req, res, next) => {
  err.messages = [];

  if(err.messageKeys) {
    for (var i = 0; i < err.messageKeys.length; i++) {
      err.messages.push(req.getMessage(err.messageKeys[i]));
    }
  }

  if(err.messages.length === 0) {
    err.messages = [req.getMessage('unexpected-error')];
  }

  return res.status(err.code || 500).send(err);
};
