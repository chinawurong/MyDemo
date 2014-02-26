package com.android.test.friend.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.text.TextUtils;

import com.android.test.friend.Friend;
import com.android.test.friend.pinyin.PinYin;

/** 通讯录工具类
 * @author wanglei */
public class ContactsUtil {

	/** 查询所有联系人的姓名，电话，邮箱 */
	public static List<Friend> getContacts(ContentResolver resolver) {
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, new String[] { Phone.DISPLAY_NAME, Phone.NUMBER }, null, null, "sort_key COLLATE LOCALIZED asc");
		List<Friend> friends = new ArrayList<Friend>();
		while (phoneCursor.moveToNext()) {
			String phoneNumber = phoneCursor.getString(1);
			if (TextUtils.isEmpty(phoneNumber) && phoneNumber.length() != 11)
				continue;
			String contactName = phoneCursor.getString(0);
			Friend f = new Friend();
			f.setName(contactName);
			f.setSortKey(PinYin.getPinYin(contactName));
			phoneNumber = phoneNumber.replaceAll(" ", "");
			phoneNumber = phoneNumber.replaceAll("-", "");
			if (phoneNumber.length() > 11)
				phoneNumber = phoneNumber.substring(phoneNumber.length() - 11, phoneNumber.length());
			f.setMobileNo(phoneNumber);
			friends.add(f);
		}
		phoneCursor.close();
		return friends;
	}

	/** 查询所有联系人的姓名，电话，邮箱 */
	public static List<Friend> getContacts2(ContentResolver resolver) {
		Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, new String[] { Phone.DISPLAY_NAME, Phone.NUMBER }, null, null, "sort_key COLLATE LOCALIZED asc");
		List<Friend> friends = new ArrayList<Friend>();
		while (phoneCursor.moveToNext()) {
			String phoneNumber = phoneCursor.getString(1);
			if (TextUtils.isEmpty(phoneNumber))
				continue;
			phoneNumber = phoneNumber.replaceAll(" ", "");
			phoneNumber = phoneNumber.replaceAll("-", "");
			if (phoneNumber.matches("^(1)\\d{10}$")) {
				String contactName = phoneCursor.getString(0);
				Friend f = new Friend();
				f.setName(contactName);
				f.setSortKey(PinYin.getPinYin(contactName));
				f.setMobileNo(phoneNumber);
				friends.add(f);
			}
		}
		phoneCursor.close();
		return friends;
	}
}
