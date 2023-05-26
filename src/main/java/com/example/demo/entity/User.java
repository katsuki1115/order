package com.example.demo.entity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.common.PasswordHasher;
import com.example.demo.common.ValidationGroups.Create;
import com.example.demo.common.ValidationGroups.Update;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "users")
public class User extends AbstractEntity implements UserDetails {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 255, nullable = false, unique = true)
	@NotEmpty(groups = { Create.class, Update.class }, message = "メールアドレスは必須要項です")
	@Email(groups = { Create.class, Update.class }, message = "メールアドレスの形式が不正です")
	private String email;
	
	@Column(length = 255, nullable = false)
	@NotEmpty(groups = { Create.class, Update.class }, message = "パスワードは必須項目です")
	@Email(groups = { Create.class, Update.class }, message = "価格は0以上の整数を入力してください")
	private String password;
	
	@Transient
	private Boolean auth;
	
	//平文を暗号文にしてpasswordに設定する
	public void encodePassword(String rawPassword) {
		if(!"".equals(rawPassword)) {
			this.setPassword(PasswordHasher.create(rawPassword));
		}
	}
	
	//ユーザーに与えられる権限リストを返却するメソッド
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	//パスワードを返却するメソッド
	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return this.password;
	}

	//ユーザー名を返却するメソッド
	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return this.email;
	}

	//アカウントの有効期限の状態を判定するメソッド
	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	//資格情報の有効期限の状態を判定するメソッド
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	//有効なユーザーが判定するメソッド
	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	//アカウントのロック状態を判定するメソッド
	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
