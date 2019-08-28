package com.tt.shiro;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tt.pojo.SysEmp;
import com.tt.sys.mapper.SysMenuMapper;
import com.tt.sys.mapper.SysRoleMenuMapper;
import com.tt.sys.mapper.SysUserMapper;
import com.tt.sys.mapper.SysUserRoleMapper;


@Service
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public void setCredentialsMatcher(
			CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher=
				new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		System.out.println("ShiroUserRealm.setCredentialsMatcher()");
		super.setCredentialsMatcher(cMatcher);
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		//1.获取用户名
		UsernamePasswordToken upToken=(UsernamePasswordToken)token;
		String username = upToken.getUsername();
		//2.基于用户查询用户信息
		SysEmp user = sysUserMapper.findUserByUserName(username);
		//3.判断用户是否存在
		if(user==null)throw new UnknownAccountException();
		//4.判断用户是否已经被禁用
		if(user.getValid()==0)throw new LockedAccountException();
		//5.封装用户信息
		ByteSource credentialsSalt=ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user,user.getPassword(),credentialsSalt,getName());
		System.out.println("ShiroUserRealm.doGetAuthenticationInfo()");
		//6.返回封装结果
		return info;
	}
	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		//1.获取登录用户信息，例如用户id
		SysEmp user=(SysEmp)arg0.getPrimaryPrincipal();
		Integer userId=user.getId();
		//2.基于用户id获取用户拥有的角色(sys_user_roles)
		List<Integer> roleIds=
				sysUserRoleMapper.findRoleIdsByUserId(userId);
		if(roleIds==null||roleIds.size()==0)
			throw new AuthorizationException();
		//3.基于角色id获取菜单id(sys_role_menus)
		Integer[] array={};
		List<Integer> menuIds=
				sysRoleMenuMapper.findMenuIdsByRoleIds(
						roleIds.toArray(array));
		if(menuIds==null||menuIds.size()==0)
			throw new AuthorizationException();
		//4.基于菜单id获取权限标识(sys_menus)
		List<String> permissions=
				sysMenuMapper.findPermissions(
						menuIds.toArray(array));
		//5.对权限标识信息进行封装并返回
		Set<String> set=new HashSet<>();
		for(String per:permissions){
			if(!StringUtils.isEmpty(per)){
				set.add(per);
			}
		}
		SimpleAuthorizationInfo info=
				new SimpleAuthorizationInfo();
		info.setStringPermissions(set);
		//返回给授权管理器
		return info;
	}

}



