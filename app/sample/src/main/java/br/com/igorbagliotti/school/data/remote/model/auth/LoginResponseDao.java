package br.com.igorbagliotti.school.data.remote.model.auth;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "loginResponse".
*/
public class LoginResponseDao extends AbstractDao<LoginResponse, Long> {

    public static final String TABLENAME = "loginResponse";

    /**
     * Properties of entity LoginResponse.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Pk = new Property(0, Long.class, "pk", true, "_id");
        public final static Property Id = new Property(1, String.class, "id", false, "ID");
        public final static Property Cpf = new Property(2, String.class, "cpf", false, "cpf");
        public final static Property Nome = new Property(3, String.class, "nome", false, "nome");
        public final static Property Endereco = new Property(4, String.class, "endereco", false, "endereco");
        public final static Property Estado = new Property(5, String.class, "estado", false, "estado");
        public final static Property Municipio = new Property(6, String.class, "municipio", false, "municipio");
        public final static Property Telefone = new Property(7, String.class, "telefone", false, "telefone");
        public final static Property Email = new Property(8, String.class, "email", false, "email");
        public final static Property Senha = new Property(9, String.class, "senha", false, "senha");
        public final static Property Ativo = new Property(10, Boolean.class, "ativo", false, "ativo");
        public final static Property DataCadastro = new Property(11, String.class, "dataCadastro", false, "data_cadastro");
    }


    public LoginResponseDao(DaoConfig config) {
        super(config);
    }
    
    public LoginResponseDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"loginResponse\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: pk
                "\"ID\" TEXT," + // 1: id
                "\"cpf\" TEXT," + // 2: cpf
                "\"nome\" TEXT," + // 3: nome
                "\"endereco\" TEXT," + // 4: endereco
                "\"estado\" TEXT," + // 5: estado
                "\"municipio\" TEXT," + // 6: municipio
                "\"telefone\" TEXT," + // 7: telefone
                "\"email\" TEXT," + // 8: email
                "\"senha\" TEXT," + // 9: senha
                "\"ativo\" INTEGER," + // 10: ativo
                "\"data_cadastro\" TEXT);"); // 11: dataCadastro
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"loginResponse\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LoginResponse entity) {
        stmt.clearBindings();
 
        Long pk = entity.getPk();
        if (pk != null) {
            stmt.bindLong(1, pk);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String cpf = entity.getCpf();
        if (cpf != null) {
            stmt.bindString(3, cpf);
        }
 
        String nome = entity.getNome();
        if (nome != null) {
            stmt.bindString(4, nome);
        }
 
        String endereco = entity.getEndereco();
        if (endereco != null) {
            stmt.bindString(5, endereco);
        }
 
        String estado = entity.getEstado();
        if (estado != null) {
            stmt.bindString(6, estado);
        }
 
        String municipio = entity.getMunicipio();
        if (municipio != null) {
            stmt.bindString(7, municipio);
        }
 
        String telefone = entity.getTelefone();
        if (telefone != null) {
            stmt.bindString(8, telefone);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(9, email);
        }
 
        String senha = entity.getSenha();
        if (senha != null) {
            stmt.bindString(10, senha);
        }
 
        Boolean ativo = entity.getAtivo();
        if (ativo != null) {
            stmt.bindLong(11, ativo ? 1L: 0L);
        }
 
        String dataCadastro = entity.getDataCadastro();
        if (dataCadastro != null) {
            stmt.bindString(12, dataCadastro);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LoginResponse entity) {
        stmt.clearBindings();
 
        Long pk = entity.getPk();
        if (pk != null) {
            stmt.bindLong(1, pk);
        }
 
        String id = entity.getId();
        if (id != null) {
            stmt.bindString(2, id);
        }
 
        String cpf = entity.getCpf();
        if (cpf != null) {
            stmt.bindString(3, cpf);
        }
 
        String nome = entity.getNome();
        if (nome != null) {
            stmt.bindString(4, nome);
        }
 
        String endereco = entity.getEndereco();
        if (endereco != null) {
            stmt.bindString(5, endereco);
        }
 
        String estado = entity.getEstado();
        if (estado != null) {
            stmt.bindString(6, estado);
        }
 
        String municipio = entity.getMunicipio();
        if (municipio != null) {
            stmt.bindString(7, municipio);
        }
 
        String telefone = entity.getTelefone();
        if (telefone != null) {
            stmt.bindString(8, telefone);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(9, email);
        }
 
        String senha = entity.getSenha();
        if (senha != null) {
            stmt.bindString(10, senha);
        }
 
        Boolean ativo = entity.getAtivo();
        if (ativo != null) {
            stmt.bindLong(11, ativo ? 1L: 0L);
        }
 
        String dataCadastro = entity.getDataCadastro();
        if (dataCadastro != null) {
            stmt.bindString(12, dataCadastro);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public LoginResponse readEntity(Cursor cursor, int offset) {
        LoginResponse entity = new LoginResponse( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // pk
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // cpf
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // nome
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // endereco
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // estado
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // municipio
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // telefone
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // email
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // senha
            cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0, // ativo
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11) // dataCadastro
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LoginResponse entity, int offset) {
        entity.setPk(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCpf(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNome(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setEndereco(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setEstado(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setMunicipio(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setTelefone(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setEmail(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSenha(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAtivo(cursor.isNull(offset + 10) ? null : cursor.getShort(offset + 10) != 0);
        entity.setDataCadastro(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LoginResponse entity, long rowId) {
        entity.setPk(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LoginResponse entity) {
        if(entity != null) {
            return entity.getPk();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LoginResponse entity) {
        return entity.getPk() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
