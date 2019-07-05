package com.cashier.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cashier.dao.LevelMapper;
import com.cashier.entity.Level;
import com.cashier.service.LevelService;

/**
 *
 * @ClassName: LevelServiceImpl
 * @description 会员等级表Service实现层
 *
 * @author dujiawei
 * @createDate 2019年6月20日
 */
@Service
public class LevelServiceImpl implements LevelService {
	
	@Resource
	private LevelMapper levelMapper;

	/**
	 * @Title: allLevel
	 * @description 所有的会员等级（无分页）
	 * @return List<Level>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public List<Level> allLevel() {
		// TODO Auto-generated method stub
		return levelMapper.allLevel();
	}

	/**
     * @Title: listLevel
     * @description 分页显示会员等级列表
     * @param @param level
     * @return List<Level>    
     * @author dujiawei
     * @createDate 2019年6月20日
     */
	@Override
	public List<Level> listLevel(Level level) {
		// TODO Auto-generated method stub
		return levelMapper.listLevel(level);
	}

	/**
	 * @Title: countLevel
	 * @description 查询会员等级的数据条数
	 * @return Level    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public Level countLevel() {
		// TODO Auto-generated method stub
		return levelMapper.countLevel();
	}

	/**
	 * @Title: saveLevel
	 * @description 新增会员等级
	 * @param @param level
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public int saveLevel(Level level) {
		// TODO Auto-generated method stub
		int num = 0;
		try {
			// 判断新增的区间是否和已存在的区间有冲突
			List<Level> ldList = levelMapper.listLevelNumber(level);
			if (ldList.size() > 0) {
				for (int i = 0; i < ldList.size(); i++) {
					if (ldList.get(i).getMinimum().compareTo(level.getMinimum()) == 0
							|| ldList.get(i).getMaximum().compareTo(level.getMaximum()) == 0
							|| ldList.get(i).getLevel().equals(level.getLevel()) 
							|| ldList.get(i).getMinimum().compareTo(level.getMaximum()) == 0
							|| ldList.get(i).getMaximum().compareTo(level.getMinimum()) == 0
							|| level.getMinimum().compareTo(ldList.get(i).getMinimum()) ==1 
							&& level.getMinimum().compareTo(ldList.get(i).getMaximum()) == -1 
							|| level.getMaximum().compareTo(level.getMinimum()) != 1 
							|| level.getMaximum().compareTo(ldList.get(i).getMinimum()) == 1
							&& level.getMaximum().compareTo(ldList.get(i).getMaximum()) == -1 
							|| level.getMinimum().compareTo(BigDecimal.ZERO) == -1
							|| level.getMaximum().compareTo(BigDecimal.ZERO) == -1) {
						
						return -1;
					}
				}
			}
			num = levelMapper.saveLevel(level); //执行新增会员等级方法
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	/**
	 * @Title: listLevelForUpdate
	 * @description 修改会员等级时查重使用
	 * @param @param level
	 * @return List<Level>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public List<Level> listLevelForUpdate(Level level) {
		// TODO Auto-generated method stub
		return levelMapper.listLevelForUpdate(level);
	}

	/**
     * @Title: updateLevel
     * @description 修改会员等级
     * @param @param level
     * @return int    
     * @author dujiawei
     * @createDate 2019年6月20日
     */
	@Override
	public int updateLevel(Level level) {
		// TODO Auto-generated method stub
		int num = 0;
		// 查询除自己的所有金额，判断是否已存在-crList
		List<Level> ldList = levelMapper.listLevelForUpdate(level);
		if(ldList.size() > 0) {
			for(int i = 0; i < ldList.size(); i++){  //判断折扣是否重复
				if(ldList.get(i).getLevel().compareTo(level.getLevel()) == 0){
					
					return -1;
				}
			}
		}
		try {
			num = levelMapper.updateLevel(level);
			if (num != 0) {
				//System.out.println("修改会员等级优惠成功！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num;
	}

	/**
	 * @Title: removeLevel
	 * @description 删除会员等级
	 * @param @param id
	 * @return int    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public int removeLevel(BigInteger id) {
		// TODO Auto-generated method stub
		return levelMapper.removeLevel(id);
	}

	/**
	 * @Title: listLevelNumber
	 * @description （新增会员等级查重判断）查询所有会员等级区间的数字
	 * @param @param level
	 * @return List<Level>    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public List<Level> listLevelNumber(Level level) {
		// TODO Auto-generated method stub
		return levelMapper.listLevelNumber(level);
	}

	/**
	 * @Title: getMinLevelId
	 * @description 查询最低的会员等级和对应的id
	 * @param @param level
	 * @return Level    
	 * @author dujiawei
	 * @createDate 2019年6月20日
	 */
	@Override
	public Level getMinLevelId(Level level) {
		// TODO Auto-generated method stub
		return levelMapper.getMinLevelId(level);
	}

}
