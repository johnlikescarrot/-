from androguard.core.apk import APK
apk = APK('/home/user/-/work/base.apk')
print('package:', apk.get_package())
print('min/target/max sdk:', apk.get_min_sdk_version(), apk.get_target_sdk_version(), apk.get_max_sdk_version())
print('version:', apk.get_androidversion_code(), apk.get_androidversion_name())
perms = apk.get_permissions()
print('permissions count:', len(perms))
print('sample perms:', perms[:20])
print('activities:', len(apk.get_activities()), 'services:', len(apk.get_services()),
      'receivers:', len(apk.get_receivers()), 'providers:', len(apk.get_providers()))
print('dex count:', len(apk.get_dex()))
print('OK')
