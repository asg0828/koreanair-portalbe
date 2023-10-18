package com.bdp.ap.config.sql;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.json.JSONObject;

@MappedTypes(value = { JSONObject.class })
public class VarcharToJSONObjectTypeHandler extends BaseTypeHandler<JSONObject> {

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, JSONObject parameter, JdbcType jdbcType)
			throws SQLException {
		if (parameter == null) {
			parameter = new JSONObject();
		}
		ps.setObject(i, parameter.toString());
	}

	@Override
	public JSONObject getNullableResult(ResultSet rs, String columnName) throws SQLException {
		JSONObject result = null;

		String str = rs.getString(columnName);
		if (StringUtils.startsWith(str, "{") && StringUtils.endsWith(str, "}")) {
			result = new JSONObject(str);
		}

		if (result == null) {
			result = new JSONObject();
		}
		return result;
	}

	@Override
	public JSONObject getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		JSONObject result = null;

		String str = rs.getString(columnIndex);
		if (StringUtils.startsWith(str, "{") && StringUtils.endsWith(str, "}")) {
			result = new JSONObject(str);
		}

		if (result == null) {
			result = new JSONObject();
		}
		return result;
	}

	@Override
	public JSONObject getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		JSONObject result = null;

		String str = cs.getString(columnIndex);
		if (StringUtils.startsWith(str, "{") && StringUtils.endsWith(str, "}")) {
			result = new JSONObject(str);
		}

		if (result == null) {
			result = new JSONObject();
		}
		return result;
	}

}
