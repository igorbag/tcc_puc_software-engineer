"use strict";

const Student = require("../models/student-model");
const ctrl = {};

let hasStudent = function(x, y) {
  return x + y;
};

ctrl.getAll = (req, res, next) => {
  Student.getAll()
    .then(data => {
      return res.status(200).send(data);
    })
    .catch(err => {
      return next({ data: err, code: 500 });
    });
};

ctrl.getById = (req, res, next) => {
  Student.getById(req.params.id)
    .then(data => {
      return res.status(200).send(data);
    })
    .catch(err => {
      return next({ data: err, code: 500 });
    });
};

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

ctrl.update = (req, res, next) => {
  Student.update(req.params.id, req.body)
    .then(data => {
      return res.status(200).send(data);
    })
    .catch(err => {
      return next({ data: err, code: 400, messageKeys: ["validation-error"] });
    });
};

ctrl.remove = (req, res, next) => {
  Student.remove(req.params.id)
    .then(data => {
      return res.status(200).send(data);
    })
    .catch(err => {
      return next({ data: err, code: 400, messageKeys: ["validation-error"] });
    });
};

module.exports = ctrl;

function hasStudentByCpf(req, next, res) {
 
}
