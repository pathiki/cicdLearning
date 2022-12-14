<?xml version="1.0" encoding="UTF-8"?>

<Values version="2.0">
  <value name="name">parse</value>
  <array name="sig" type="value" depth="1">
    <value>[i] field:0:required inString</value>
    <value>[i] field:0:optional outputRecord {"true","false"}</value>
    <value>[i] field:0:optional maxLength</value>
    <value>[i] field:0:optional delimitor</value>
    <value>[i] record:1:optional fields</value>
    <value>[i] - field:0:required name</value>
    <value>[i] - field:0:optional startIndex</value>
    <value>[i] - field:0:optional length</value>
    <value>[i] field:0:optional ignoreWhitespace {"true","false"}</value>
    <value>[o] record:0:optional record</value>
    <value>[o] field:1:optional list</value>
  </array>
  <value name="sigtype">java 3.5</value>
  <value name="encodeutf8">true</value>
  <value name="body">SURhdGFDdXJzb3IgY3Vyc29yID0gcGlwZWxpbmUuZ2V0Q3Vyc29yKCk7DQoNClN0cmluZyBsaW5l
ID0gIiI7DQpib29sZWFuIG91dHB1dFJlY29yZCA9IHRydWU7DQpib29sZWFuIGlnbm9yZVdoaXRl
c3BhY2UgPSB0cnVlOw0KU3RyaW5nIGRlbGltaXRvciA9IG51bGw7DQpTdHJpbmdbXSB0b2tlbnMg
PSBudWxsOw0KDQppZiAoY3Vyc29yLmZpcnN0KCJpblN0cmluZyIpKQ0Kew0KICBsaW5lID0gKFN0
cmluZykgY3Vyc29yLmdldFZhbHVlKCk7DQogIGlmIChjdXJzb3IuZmlyc3QoImlnbm9yZVdoaXRl
c3BhY2UiKSkNCiAgew0KICAgIGlnbm9yZVdoaXRlc3BhY2UgPSBCb29sZWFuLnZhbHVlT2YoKFN0
cmluZykgY3Vyc29yLmdldFZhbHVlKCkpLmJvb2xlYW5WYWx1ZSgpOw0KICB9DQoNCiAgaWYgKGN1
cnNvci5maXJzdCgib3V0cHV0UmVjb3JkIikpDQogIHsNCiAgICBvdXRwdXRSZWNvcmQgPSBCb29s
ZWFuLnZhbHVlT2YoKFN0cmluZykgY3Vyc29yLmdldFZhbHVlKCkpLmJvb2xlYW5WYWx1ZSgpOw0K
ICB9DQoNCiAgLy8gQ3JlYXRlIGFuIGFycmF5IG9mIHRva2VucyBmcm9tIHRoZSBTdHJpbmcgdXNp
bmcgdGhlIGRlbGltaXRvcg0KICBpZiAoY3Vyc29yLmZpcnN0KCJkZWxpbWl0b3IiKSkNCiAgew0K
ICAgIGRlbGltaXRvciA9IChTdHJpbmcpIGN1cnNvci5nZXRWYWx1ZSgpOw0KICAgIGlmIChkZWxp
bWl0b3IgIT0gbnVsbCAmJiBkZWxpbWl0b3IubGVuZ3RoKCkgPiAwKQ0KICAgIHsNCiAgICAgIHRv
a2VucyA9IHRva2VuaXplKGxpbmUsIGRlbGltaXRvcik7DQogICAgfQ0KICB9DQoNCiAgLy8gSWYg
dGhlIG91dHB1dCBpcyB0byBiZSBpbiBhIHJlY29yZCBmb3JtYXQNCiAgaWYgKG91dHB1dFJlY29y
ZCkNCiAgew0KICAgIC8vIENyZWF0ZSB0aGUgb3V0cHV0IHJlY29yZCB0byBob2xkIHRoZSBmaWVs
ZHMNCiAgICBJRGF0YSBvdXRSZWNvcmQgPSBJRGF0YUZhY3RvcnkuY3JlYXRlKCk7DQogICAgSURh
dGFDdXJzb3Igb3V0UmVjb3JkQ3Vyc29yID0gb3V0UmVjb3JkLmdldEN1cnNvcigpOw0KDQogICAg
aWYgKGN1cnNvci5maXJzdCgiZmllbGRzIikpDQogICAgew0KICAgICAgLy8gR2V0IHRoZSBmaWVs
ZHMgbGlzdA0KICAgICAgSURhdGFbXSBmaWVsZHMgPSAoSURhdGFbXSkgY3Vyc29yLmdldFZhbHVl
KCk7DQogICAgICBmb3IgKGludCBpID0gMDsgaSA8IGZpZWxkcy5sZW5ndGg7IGkrKykNCiAgICAg
IHsNCiAgICAgICAgLy8gRXh0cmFjdCB0aGUgbmFtZSBvZiB0aGUgY3VycmVudCBmaWVsZA0KICAg
ICAgICBJRGF0YUN1cnNvciBmaWVsZEN1cnNvciA9IGZpZWxkc1tpXS5nZXRDdXJzb3IoKTsNCiAg
ICAgICAgaWYgKGRlbGltaXRvciAhPSBudWxsICYmIGRlbGltaXRvci5sZW5ndGgoKSA+IDApDQog
ICAgICAgICAgew0KICAgICAgICAgICAgU3RyaW5nIG5hbWUgPSBudWxsOw0KICAgICAgICAgICAg
aWYgKGZpZWxkQ3Vyc29yLmZpcnN0KCJuYW1lIikpDQogICAgICAgICAgICB7DQogICAgICAgICAg
ICAgIG5hbWUgPSAoU3RyaW5nKSBmaWVsZEN1cnNvci5nZXRWYWx1ZSgpOw0KICAgICAgICAgICAg
fQ0KICAgICAgICAgICAgZWxzZQ0KICAgICAgICAgICAgew0KICAgICAgICAgICAgICB0cnkgew0K
CQkJCXRocm93IG5ldyBFeGNlcHRpb24oIkZpZWxkIGRvZXMgbm90IGhhdmUgYSAnbmFtZScgYXR0
cmlidXRlIik7DQoJCQl9IGNhdGNoIChFeGNlcHRpb24gZSkgew0KCQkJCS8vIFRPRE8gQXV0by1n
ZW5lcmF0ZWQgY2F0Y2ggYmxvY2sNCgkJCQllLnByaW50U3RhY2tUcmFjZSgpOw0KCQkJfQ0KICAg
ICAgICAgICAgICANCiAgICAgICAgICAgIH0NCg0KICAgICAgICAgICAgU3RyaW5nIHRva2VuID0g
dG9rZW5zW2ldOw0KICAgICAgICAgICAgb3V0UmVjb3JkQ3Vyc29yLmluc2VydEFmdGVyKG5hbWUs
IGlnbm9yZVdoaXRlc3BhY2UgPyB0b2tlbi50cmltKCkubGVuZ3RoKCkgPiAwID8gdG9rZW4udHJp
bSgpIDogbnVsbCA6IHRva2VuKTsNCiAgICAgICAgICB9DQogICAgICAgICBlbHNlDQogICAgICAg
ICB7DQogICAgICAgICAgICANCiAgICAgICAgICB9DQogICAgICAgIGZpZWxkQ3Vyc29yLmRlc3Ry
b3koKTsNCiAgICAgIH0NCiAgICB9DQogICAgZWxzZQ0KICAgIHsNCiAgICAgIHRocm93IG5ldyBT
ZXJ2aWNlRXhjZXB0aW9uKCJJbnB1dCAnZmllbGRzJyBtdXN0IGV4aXN0IHdoZW4gb3V0cHV0UmVj
b3JkIGlzIHNldCB0byAndHJ1ZSciKTsNCiAgICB9DQoNCiAgICBvdXRSZWNvcmRDdXJzb3IuZGVz
dHJveSgpOw0KICAgIElEYXRhVXRpbC5wdXQoY3Vyc29yLCAicmVjb3JkIiwgb3V0UmVjb3JkKTsN
CiAgfQ0KICBlbHNlDQogIHsNCiAgICAvLyBTaW1wbGUgU3RyaW5nIExpc3QgbmVlZGVkDQogICAg
U3RyaW5nW10gYXJyYXk7DQoNCiAgICBpZiAoZGVsaW1pdG9yICE9IG51bGwgJiYgZGVsaW1pdG9y
Lmxlbmd0aCgpID4gMCkNCiAgICB7DQogICAgICBhcnJheSA9IG5ldyBTdHJpbmdbdG9rZW5zLmxl
bmd0aF07DQogICAgICBmb3IgKGludCBpID0gMDsgaSA8IGFycmF5Lmxlbmd0aDsgaSsrKQ0KICAg
ICAgew0KICAgICAgICBhcnJheVtpXSA9IGlnbm9yZVdoaXRlc3BhY2UgPyB0b2tlbnNbaV0udHJp
bSgpLmxlbmd0aCgpID4gMCA/IHRva2Vuc1tpXS50cmltKCkgOiBudWxsIDogdG9rZW5zW2ldOw0K
ICAgICAgfQ0KICAgIH0NCiAgICBlbHNlDQogICAgew0KICAgICAgaW50IG1heExlbmd0aCA9IDA7
DQoNCiAgICAgIGlmIChjdXJzb3IuZmlyc3QoIm1heExlbmd0aCIpKQ0KICAgICAgew0KICAgICAg
ICBtYXhMZW5ndGggPSBJbnRlZ2VyLnBhcnNlSW50KChTdHJpbmcpIGN1cnNvci5nZXRWYWx1ZSgp
KTsNCiAgICAgIH0NCg0KICAgICAgaWYgKG1heExlbmd0aCA8PSAwKQ0KICAgICAgew0KICAgICAg
ICB0aHJvdyBuZXcgU2VydmljZUV4Y2VwdGlvbigiSW5wdXQgJ21heExlbmd0aCcgb3IgJ2RlbGlt
aXRvcicgbXVzdCBleGlzdCB3aGVuIG91dHB1dFJlY29yZCBpcyBzZXQgdG8gJ2ZhbHNlJyIpOw0K
ICAgICAgfQ0KDQogICAgICBhcnJheSA9IG5ldyBTdHJpbmdbbGluZS5sZW5ndGgoKSAvIG1heExl
bmd0aCArIDFdOw0KICAgICAgZm9yIChpbnQgaSA9IDA7IGkgPCBhcnJheS5sZW5ndGg7IGkrKykN
CiAgICAgIHsNCiAgICAgICAgaWYgKChpICsgMSkgKiBtYXhMZW5ndGggPiBsaW5lLmxlbmd0aCgp
KQ0KICAgICAgICB7DQogICAgICAgICAgU3RyaW5nIHZhbHVlID0gbGluZS5zdWJzdHJpbmcoaSAq
IG1heExlbmd0aCk7DQogICAgICAgICAgYXJyYXlbaV0gPSBpZ25vcmVXaGl0ZXNwYWNlID8gdmFs
dWUudHJpbSgpLmxlbmd0aCgpID4gMCA/IHZhbHVlLnRyaW0oKSA6IG51bGwgOiB2YWx1ZTsNCiAg
ICAgICAgfQ0KICAgICAgICBlbHNlDQogICAgICAgIHsNCiAgICAgICAgICBTdHJpbmcgdmFsdWUg
PSBsaW5lLnN1YnN0cmluZyhpICogbWF4TGVuZ3RoLCAoaSArIDEpICogbWF4TGVuZ3RoKTsNCiAg
ICAgICAgICBhcnJheVtpXSA9IGlnbm9yZVdoaXRlc3BhY2UgPyB2YWx1ZS50cmltKCkubGVuZ3Ro
KCkgPiAwID8gdmFsdWUudHJpbSgpIDogbnVsbCA6IHZhbHVlOw0KICAgICAgICB9DQogICAgICB9
DQogICAgfQ0KICAgIElEYXRhVXRpbC5wdXQoY3Vyc29yLCAibGlzdCIsIGFycmF5KTsNCiAgfQ0K
fQ0KZWxzZQ0Kew0KICB0aHJvdyBuZXcgU2VydmljZUV4Y2VwdGlvbigiTWlzc2luZyBpbnB1dCAn
aW5TdHJpbmcnIik7DQp9DQoNCmN1cnNvci5kZXN0cm95KCk7DQoJDQo=</value>
</Values>
