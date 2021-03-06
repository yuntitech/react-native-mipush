Pod::Spec.new do |s|
  s.name         = "react-native-mipush"
  s.version      = "0.3.5"
  s.summary      = "React Native MiPush"

  s.description  = <<-DESC
                  React Native MiPush
                   DESC

  s.homepage     = "https://github.com/a289459798/react-native-mipush"
  s.license      = "MIT"
  # s.license      = { :type => "MIT", :file => "FILE_LICENSE" }
  s.author             = { "a289459798" => "289459798@qq.com" }
  s.platform     = :ios, "8.0"
  s.source       = { :git => "https://github.com/a289459798/react-native-mipush.git", :tag => "master" }
  s.source_files  = "ios/**/*.{h,m}"
  s.requires_arc = true

  s.dependency "React-Core"
  s.framework = 'UserNotifications'
  s.vendored_libraries = "ios/RCTMIPushModule/RCTMIPushModule/libMiPushSDK.a","ios/RCTMIPushModule/RCTMIPushModule/libMiPushApnsAckSDK.a"
  s.public_header_files = 'ios/RCTMIPushModule/RCTMIPushModule/*.h'

end
