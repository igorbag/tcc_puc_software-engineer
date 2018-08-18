"use strict";

const Student = require("../models/student-model");
const ctrl = {};


/**
 * Metodo responsavel por buscar todos os registros
 * 
 * @param {Request} req 
 * @param {Response} res 
 */
ctrl.getAll = (req, res, next) => {
  Student.getAll()
    .then(data => {
      return res.status(200).send(data);
    })
    .catch(err => {
      return next({ data: err, code: 500 });
    });
};

/**
 * Metodo responsavel por buscar registro por Id
 * 
 * @param {Request} req 
 * @param {Response} res 
 */
ctrl.getById = (req, res, next) => {
  Student.getById(req.params.id)
    .then(data => {
      return res.status(200).send(data);
    })
    .catch(err => {
      return next({ data: err, code: 500 });
    });
};

/**
 * Metodo responsavel por cadastrar um novo registro se nao existir
 * 
 * @param {Request} req 
 * @param {Response} res 
 */
ctrl.create = (req, res, next) => {

  Student.findBy({ cpf: req.body.cpf }).then(data => {
    if (data && data != null) {
      console.log("Eu tenho registro");
      return next({
        data: data,
        code: 400,
        messageKeys: ["user-register-invalid"]
      });
    }
  }).catch(noRegister =>{
    Student.create(req.body)
    .then(data => {
      return res.status(201).send(data);
    })
    .catch(err => {
      return next({
        data: err,
        code: 400,
        messageKeys: ["validation-error"]
      });
    });
  });
};

/**
 * Metodo responsavel por atualizar um item
 * 
 * @param {Request} req 
 * @param {Response} res 
 */
ctrl.update = (req, res, next) => {
  Student.update(req.params.id, req.body)
    .then(data => {
      return res.status(200).send(data);
    })
    .catch(err => {
      return next({ data: err, code: 400, messageKeys: ["validation-error"] });
    });
};

/**
 * Metodo responsavel por remover um item
 * 
 * @param {Request} req 
 * @param {Response} res 
 * @param {Proximo} next 
 */
ctrl.remove = (req, res, next) => {
  Student.remove(req.params.id)
    .then(data => {
      return res.status(200).send(data);
    })
    .catch(err => {
      return next({ data: err, code: 400, messageKeys: ["validation-error"] });
    });
};

/**
 * 
 * Metodo responsavel por prover autenticacao
 *
 * @param {Request} req 
 * @param {Response} res 
 */
ctrl.auth = (req, res) => {

  Student.authenticate(req.body)

    .then(data => {
      return res.status(200).send(data);
    })
    .catch(err => {
      return res.status(500).send(err);
    });
};


module.exports = ctrl;