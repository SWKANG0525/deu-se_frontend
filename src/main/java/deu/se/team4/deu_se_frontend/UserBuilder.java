/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

/**
 *
 * @author 강호동 Builder Pattern Abstract Builder Class
 */
public abstract class UserBuilder {

    protected String name_eng;
    protected String name_kor;
    protected String id;
    protected String pw;
    protected String birth;
    protected String gender;
    protected String tel;
    protected EnumAccount account_type;

    public abstract SignInformation build();

    public UserBuilder setName_eng(String name_eng) {
        this.name_eng = name_eng;
        return this;
    }

    public UserBuilder setName_kor(String name_kor) {
        this.name_kor = name_kor;
        return this;

    }

    public UserBuilder setId(String id) {
        this.id = id;
        return this;

    }

    public UserBuilder setPw(String pw) {
        this.pw = pw;
        return this;

    }

    public UserBuilder setBirth(String birth) {
        this.birth = birth;
        return this;

    }

    public UserBuilder setGender(String gender) {
        this.gender = gender;
        return this;

    }

    public UserBuilder setTel(String tel) {
        this.tel = tel;
        return this;

    }

    public UserBuilder setAccount_type(EnumAccount account_type) {
        this.account_type = account_type;
        return this;

    }

}
