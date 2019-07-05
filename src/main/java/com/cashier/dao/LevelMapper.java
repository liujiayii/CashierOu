package com.cashier.dao;

import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cashier.entity.Level;


/**
 *
 * @ClassName: LevelMapper
 * @description 会员等级表的方法
 *
 * @author dujiawei
 * @createDate 2019年6月20日
 */
public interface LevelMapper {
	
	/**
	 * @Title: allLevel
	 * @description 所有的会员等级（无分页）
	 * @return List<Level>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
    public List<Level> allLevel();
    
    /**
     * @Title: listLevel
     * @description 分页显示会员等级列表
     * @param @param level
     * @return List<Level>    
     * @author dujiawei
     * @createDate 2019年6月20日
     */
    public List<Level> listLevel(Level level);
	
	/**
	 * @Title: countLevel
	 * @description 查询会员等级的数据条数
	 * @return Level    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public Level countLevel();
	
	/**
	 * @Title: saveLevel
	 * @description 新增会员等级
	 * @param @param level
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public int saveLevel(Level level);
	
	/**
	 * @Title: listLevelForUpdate
	 * @description 修改会员等级时查重使用
	 * @param @param level
	 * @return List<Level>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
    public List<Level> listLevelForUpdate(Level level);

    /**
     * @Title: updateLevel
     * @description 修改会员等级
     * @param @param level
     * @return int    
     * @author dujiawei
     * @createDate 2019年6月20日
     */
	public int updateLevel(Level level);
	
	/**
	 * @Title: removeLevel
	 * @description 删除会员等级
	 * @param @param id
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public int removeLevel(@Param("id") BigInteger id);
	
	/**
	 * @Title: listLevelNumber
	 * @description （新增会员等级查重判断）查询所有会员等级区间的数字
	 * @param @param level
	 * @return List<Level>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public List<Level> listLevelNumber(Level level);
	
	/**
	 * @Title: getMinLevelId
	 * @description 查询最低的会员等级和对应的id
	 * @param @param level
	 * @return Level    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	public Level getMinLevelId(Level level);

}
