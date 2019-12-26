package cn.wangjie.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "xihucms_user")
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;

    /**
     * 注册邮箱
     */
    @Column(name = "Email")
    private String email;

    /**
     * 密码
     */
    @Column(name = "Password")
    private String password;

    /**
     * 头像
     */
    @Column(name = "Pic")
    private String pic;

    /**
     * 用户名/昵称
     */
    @Column(name = "UserName")
    private String userName;

    /**
     * 真实姓名
     */
    @Column(name = "TrueName")
    private String trueName;

    /**
     * 个人签名
     */
    @Column(name = "Intro")
    private String intro;

    /**
     * 攻略里面的个性签名
     */
    @Column(name = "NewIntro")
    private String newIntro;

    /**
     * 性别 1男 2女
     */
    @Column(name = "Sex")
    private Byte sex;

    /**
     * 出生日期
     */
    @Column(name = "BirthYear")
    private Integer birthYear;

    /**
     * 出生省
     */
    @Column(name = "BirthProvince")
    private String birthProvince;

    /**
     * 出生市
     */
    @Column(name = "BirthCity")
    private String birthCity;

    /**
     * 居住省
     */
    @Column(name = "LiveProvince")
    private String liveProvince;

    /**
     * 居住市
     */
    @Column(name = "LiveCity")
    private String liveCity;

    /**
     * 血型
     */
    @Column(name = "Blood")
    private Integer blood;

    /**
     * QQ号
     */
    private String QQ;

    /**
     * 电话号
     */
    @Column(name = "Phone")
    private String phone;

    /**
     * 手机号
     */
    @Column(name = "Mobile")
    private String mobile;

    /**
     * 地址
     */
    @Column(name = "Address")
    private String address;

    /**
     * 注册时间
     */
    @Column(name = "AddTime")
    private Integer addTime;

    /**
     * 是否推荐
     */
    @Column(name = "Commend")
    private Boolean commend;

    /**
     * 推荐时间
     */
    @Column(name = "CommendTime")
    private Integer commendTime;

    /**
     * 最后登录时间
     */
    @Column(name = "LastLoginTime")
    private Integer lastLoginTime;

    /**
     * 个人领地模板
     */
    @Column(name = "SpaceTemplate")
    private String spaceTemplate;

    /**
     * 状态 1 正常 0冻结
     */
    private Boolean status;

    /**
     * 用户组 gid=2为马甲
     */
    private Short gid;

    /**
     * 更新时间
     */
    private Date update_at;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 资料百分比
     */
    private Integer inforpercent;

    private String remember_token;

    /**
     * 学历
     */
    private String education;

    /**
     * 职业
     */
    private String occupation;

    /**
     * 年收入
     */
    private String revenue;

    /**
     * 出生日期-月
     */
    private Byte birthmonth;

    /**
     * 出生日期-天
     */
    private Byte birthday;

    /**
     * 所属组
     */
    private Short groupid;

    /**
     * 消费等级
     */
    private Short vip_level;

    /**
     * 扩展组
     */
    private String extgroupids;

    /**
     * 积分
     */
    private Integer credits;

    /**
     * 经验
     */
    private Integer exp;

    /**
     * 金币
     */
    private Integer golds1;

    /**
     * 消费值，不等于消费金额
     */
    private BigDecimal purchases;

    /**
     * vip会员有效期
     */
    private Integer vip_expiration;

    /**
     * 游侠币
     */
    private BigDecimal golds;

    /**
     * 个人主页背景图
     */
    private String app_bg_pic;

    /**
     * 获取主键
     *
     * @return ID - 主键
     */
    public Integer getID() {
        return ID;
    }

    /**
     * 设置主键
     *
     * @param ID 主键
     */
    public void setID(Integer ID) {
        this.ID = ID;
    }

    /**
     * 获取注册邮箱
     *
     * @return Email - 注册邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置注册邮箱
     *
     * @param email 注册邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取密码
     *
     * @return Password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取头像
     *
     * @return Pic - 头像
     */
    public String getPic() {
        return pic;
    }

    /**
     * 设置头像
     *
     * @param pic 头像
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    /**
     * 获取用户名/昵称
     *
     * @return UserName - 用户名/昵称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名/昵称
     *
     * @param userName 用户名/昵称
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取真实姓名
     *
     * @return TrueName - 真实姓名
     */
    public String getTrueName() {
        return trueName;
    }

    /**
     * 设置真实姓名
     *
     * @param trueName 真实姓名
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName == null ? null : trueName.trim();
    }

    /**
     * 获取个人签名
     *
     * @return Intro - 个人签名
     */
    public String getIntro() {
        return intro;
    }

    /**
     * 设置个人签名
     *
     * @param intro 个人签名
     */
    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    /**
     * 获取攻略里面的个性签名
     *
     * @return NewIntro - 攻略里面的个性签名
     */
    public String getNewIntro() {
        return newIntro;
    }

    /**
     * 设置攻略里面的个性签名
     *
     * @param newIntro 攻略里面的个性签名
     */
    public void setNewIntro(String newIntro) {
        this.newIntro = newIntro == null ? null : newIntro.trim();
    }

    /**
     * 获取性别 1男 2女
     *
     * @return Sex - 性别 1男 2女
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * 设置性别 1男 2女
     *
     * @param sex 性别 1男 2女
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * 获取出生日期
     *
     * @return BirthYear - 出生日期
     */
    public Integer getBirthYear() {
        return birthYear;
    }

    /**
     * 设置出生日期
     *
     * @param birthYear 出生日期
     */
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * 获取出生省
     *
     * @return BirthProvince - 出生省
     */
    public String getBirthProvince() {
        return birthProvince;
    }

    /**
     * 设置出生省
     *
     * @param birthProvince 出生省
     */
    public void setBirthProvince(String birthProvince) {
        this.birthProvince = birthProvince == null ? null : birthProvince.trim();
    }

    /**
     * 获取出生市
     *
     * @return BirthCity - 出生市
     */
    public String getBirthCity() {
        return birthCity;
    }

    /**
     * 设置出生市
     *
     * @param birthCity 出生市
     */
    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity == null ? null : birthCity.trim();
    }

    /**
     * 获取居住省
     *
     * @return LiveProvince - 居住省
     */
    public String getLiveProvince() {
        return liveProvince;
    }

    /**
     * 设置居住省
     *
     * @param liveProvince 居住省
     */
    public void setLiveProvince(String liveProvince) {
        this.liveProvince = liveProvince == null ? null : liveProvince.trim();
    }

    /**
     * 获取居住市
     *
     * @return LiveCity - 居住市
     */
    public String getLiveCity() {
        return liveCity;
    }

    /**
     * 设置居住市
     *
     * @param liveCity 居住市
     */
    public void setLiveCity(String liveCity) {
        this.liveCity = liveCity == null ? null : liveCity.trim();
    }

    /**
     * 获取血型
     *
     * @return Blood - 血型
     */
    public Integer getBlood() {
        return blood;
    }

    /**
     * 设置血型
     *
     * @param blood 血型
     */
    public void setBlood(Integer blood) {
        this.blood = blood;
    }

    /**
     * 获取QQ号
     *
     * @return QQ - QQ号
     */
    public String getQQ() {
        return QQ;
    }

    /**
     * 设置QQ号
     *
     * @param QQ QQ号
     */
    public void setQQ(String QQ) {
        this.QQ = QQ == null ? null : QQ.trim();
    }

    /**
     * 获取电话号
     *
     * @return Phone - 电话号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号
     *
     * @param phone 电话号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取手机号
     *
     * @return Mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取地址
     *
     * @return Address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取注册时间
     *
     * @return AddTime - 注册时间
     */
    public Integer getAddTime() {
        return addTime;
    }

    /**
     * 设置注册时间
     *
     * @param addTime 注册时间
     */
    public void setAddTime(Integer addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取是否推荐
     *
     * @return Commend - 是否推荐
     */
    public Boolean getCommend() {
        return commend;
    }

    /**
     * 设置是否推荐
     *
     * @param commend 是否推荐
     */
    public void setCommend(Boolean commend) {
        this.commend = commend;
    }

    /**
     * 获取推荐时间
     *
     * @return CommendTime - 推荐时间
     */
    public Integer getCommendTime() {
        return commendTime;
    }

    /**
     * 设置推荐时间
     *
     * @param commendTime 推荐时间
     */
    public void setCommendTime(Integer commendTime) {
        this.commendTime = commendTime;
    }

    /**
     * 获取最后登录时间
     *
     * @return LastLoginTime - 最后登录时间
     */
    public Integer getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLoginTime 最后登录时间
     */
    public void setLastLoginTime(Integer lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取个人领地模板
     *
     * @return SpaceTemplate - 个人领地模板
     */
    public String getSpaceTemplate() {
        return spaceTemplate;
    }

    /**
     * 设置个人领地模板
     *
     * @param spaceTemplate 个人领地模板
     */
    public void setSpaceTemplate(String spaceTemplate) {
        this.spaceTemplate = spaceTemplate == null ? null : spaceTemplate.trim();
    }

    /**
     * 获取状态 1 正常 0冻结
     *
     * @return status - 状态 1 正常 0冻结
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * 设置状态 1 正常 0冻结
     *
     * @param status 状态 1 正常 0冻结
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * 获取用户组 gid=2为马甲
     *
     * @return gid - 用户组 gid=2为马甲
     */
    public Short getGid() {
        return gid;
    }

    /**
     * 设置用户组 gid=2为马甲
     *
     * @param gid 用户组 gid=2为马甲
     */
    public void setGid(Short gid) {
        this.gid = gid;
    }

    /**
     * 获取更新时间
     *
     * @return update_at - 更新时间
     */
    public Date getUpdate_at() {
        return update_at;
    }

    /**
     * 设置更新时间
     *
     * @param update_at 更新时间
     */
    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * 设置创建时间
     *
     * @param create_time 创建时间
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * 获取资料百分比
     *
     * @return inforpercent - 资料百分比
     */
    public Integer getInforpercent() {
        return inforpercent;
    }

    /**
     * 设置资料百分比
     *
     * @param inforpercent 资料百分比
     */
    public void setInforpercent(Integer inforpercent) {
        this.inforpercent = inforpercent;
    }

    /**
     * @return remember_token
     */
    public String getRemember_token() {
        return remember_token;
    }

    /**
     * @param remember_token
     */
    public void setRemember_token(String remember_token) {
        this.remember_token = remember_token == null ? null : remember_token.trim();
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * 获取职业
     *
     * @return occupation - 职业
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * 设置职业
     *
     * @param occupation 职业
     */
    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    /**
     * 获取年收入
     *
     * @return revenue - 年收入
     */
    public String getRevenue() {
        return revenue;
    }

    /**
     * 设置年收入
     *
     * @param revenue 年收入
     */
    public void setRevenue(String revenue) {
        this.revenue = revenue == null ? null : revenue.trim();
    }

    /**
     * 获取出生日期-月
     *
     * @return birthmonth - 出生日期-月
     */
    public Byte getBirthmonth() {
        return birthmonth;
    }

    /**
     * 设置出生日期-月
     *
     * @param birthmonth 出生日期-月
     */
    public void setBirthmonth(Byte birthmonth) {
        this.birthmonth = birthmonth;
    }

    /**
     * 获取出生日期-天
     *
     * @return birthday - 出生日期-天
     */
    public Byte getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期-天
     *
     * @param birthday 出生日期-天
     */
    public void setBirthday(Byte birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取所属组
     *
     * @return groupid - 所属组
     */
    public Short getGroupid() {
        return groupid;
    }

    /**
     * 设置所属组
     *
     * @param groupid 所属组
     */
    public void setGroupid(Short groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取消费等级
     *
     * @return vip_level - 消费等级
     */
    public Short getVip_level() {
        return vip_level;
    }

    /**
     * 设置消费等级
     *
     * @param vip_level 消费等级
     */
    public void setVip_level(Short vip_level) {
        this.vip_level = vip_level;
    }

    /**
     * 获取扩展组
     *
     * @return extgroupids - 扩展组
     */
    public String getExtgroupids() {
        return extgroupids;
    }

    /**
     * 设置扩展组
     *
     * @param extgroupids 扩展组
     */
    public void setExtgroupids(String extgroupids) {
        this.extgroupids = extgroupids == null ? null : extgroupids.trim();
    }

    /**
     * 获取积分
     *
     * @return credits - 积分
     */
    public Integer getCredits() {
        return credits;
    }

    /**
     * 设置积分
     *
     * @param credits 积分
     */
    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    /**
     * 获取经验
     *
     * @return exp - 经验
     */
    public Integer getExp() {
        return exp;
    }

    /**
     * 设置经验
     *
     * @param exp 经验
     */
    public void setExp(Integer exp) {
        this.exp = exp;
    }

    /**
     * 获取金币
     *
     * @return golds1 - 金币
     */
    public Integer getGolds1() {
        return golds1;
    }

    /**
     * 设置金币
     *
     * @param golds1 金币
     */
    public void setGolds1(Integer golds1) {
        this.golds1 = golds1;
    }

    /**
     * 获取消费值，不等于消费金额
     *
     * @return purchases - 消费值，不等于消费金额
     */
    public BigDecimal getPurchases() {
        return purchases;
    }

    /**
     * 设置消费值，不等于消费金额
     *
     * @param purchases 消费值，不等于消费金额
     */
    public void setPurchases(BigDecimal purchases) {
        this.purchases = purchases;
    }

    /**
     * 获取vip会员有效期
     *
     * @return vip_expiration - vip会员有效期
     */
    public Integer getVip_expiration() {
        return vip_expiration;
    }

    /**
     * 设置vip会员有效期
     *
     * @param vip_expiration vip会员有效期
     */
    public void setVip_expiration(Integer vip_expiration) {
        this.vip_expiration = vip_expiration;
    }

    /**
     * 获取游侠币
     *
     * @return golds - 游侠币
     */
    public BigDecimal getGolds() {
        return golds;
    }

    /**
     * 设置游侠币
     *
     * @param golds 游侠币
     */
    public void setGolds(BigDecimal golds) {
        this.golds = golds;
    }

    /**
     * 获取个人主页背景图
     *
     * @return app_bg_pic - 个人主页背景图
     */
    public String getApp_bg_pic() {
        return app_bg_pic;
    }

    /**
     * 设置个人主页背景图
     *
     * @param app_bg_pic 个人主页背景图
     */
    public void setApp_bg_pic(String app_bg_pic) {
        this.app_bg_pic = app_bg_pic == null ? null : app_bg_pic.trim();
    }
}