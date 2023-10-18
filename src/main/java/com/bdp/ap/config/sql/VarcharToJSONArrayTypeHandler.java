package com.bdp.ap.config.sql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.json.JSONArray;

@MappedTypes(value= {JSONArray.class})
public class VarcharToJSONArrayTypeHandler extends BaseTypeHandler<JSONArray>  {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, JSONArray parameter, JdbcType jdbcType)
			throws SQLException {
		if (parameter == null) {
			parameter = new JSONArray();
		}
		ps.setObject(i, parameter.toString());
	}

	@Override
	public JSONArray getNullableResult(ResultSet rs, String columnName) throws SQLException {
		JSONArray result = null;

		String str = rs.getString(columnName);
		if (StringUtils.startsWith(str, "[") && StringUtils.endsWith(str, "]")) {
			result = new JSONArray(str);
		}

		if (result == null) {
			result = new JSONArray();
		}
		return result;
	}

	@Override
	public JSONArray getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		JSONArray result = null;

		String str = rs.getString(columnIndex);
		if (StringUtils.startsWith(str, "[") && StringUtils.endsWith(str, "]")) {
			result = new JSONArray(str);
		}

		if (result == null) {
			result = new JSONArray();
		}
		return result;
	}

	@Override
	public JSONArray getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		JSONArray result = null;

		String str = cs.getString(columnIndex);
		if (StringUtils.startsWith(str, "[") && StringUtils.endsWith(str, "]")) {
			result = new JSONArray(str);
		}

		if (result == null) {
			result = new JSONArray();
		}
		return result;
	}

}
