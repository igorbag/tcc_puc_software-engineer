"use strict";

const mongoose = require("mongoose");

const schema = new mongoose.Schema({
  id: {
    type: mongoose.Schema.Types.ObjectId
  },

  cpf: {
    type: String,
    required: true
  },

  nome: {
    type: String,
    required: true
  },

  endereco: {
    type: String,
    required: true
  },

  estado: {
    type: String,
    required: true
  },

  municipio: {
    type: String,
    required: true
  },

  telefone: {
    type: String,
    required: true
  },

  email: {
    type: String,
    required: true
  },

  senha: {
    type: String,
    required: true
  },

  data_cadastro: { type: Date, default: Date.now },

  ativo: {
    type: Boolean,
    default: true
  }
});

const Student = mongoose.model("students", schema);
const model = {};

model.getAll = (query = {}) => {
  query.ativo = true;
  return Student.find(query);
};

model.getById = _id => {
  return Student.findOne({ _id });
};

model.create = student => {
  return Student.create(student);
};

model.update = (_id, student) => {
  return Student.update({ _id }, student);
};

model.remove = _id => {
  return Student.update({ _id }, { ativo: false });
};

model.findBy = student => {
  return new Promise((resolve, reject) => {
    Student.findOne(student)
      .then(data => {
        if (data && data != null) {
          return resolve(data);
        }
        return reject();
      })
      .catch(err => {
        return reject(err);
      });
  });
};

model.authenticate = student => {
  return new Promise((resolve, reject) => {
    let _query = {
      email: student.email,
      senha: student.senha,
      ativo: true
    };

    Student.findOne(_query)
      .then(data => {
        if (data && data != null) {
          return resolve(data);
        }
        return reject();
      })
      .catch(err => {
        return reject(err);
      });
  });
};

module.exports = model;
