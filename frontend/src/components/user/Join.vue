<template>
    <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h2>회원가입</h2>
                <hr class="line">
                <div class="form-wrap ">
                    <div class="input-wrap">
                        <div class="p-2">
                            <!-- <i class="fas fa-envelope"></i> -->
                            <label for="email">이메일</label>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.email" 
                            id="email"
                            name="email"
                            placeholder=" 이메일을 입력해주세요"
                            type="text"/>
                    </div >

                    <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <!-- <i class="fas fa-key"></i> -->
                            <label for="password">비밀번호</label>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.password"
                            id="password" 
                            name="password"
                            type="password"
                            placeholder=" 비밀번호를 입력해주세요"/>
                        <span :class="{active : signUpData.passwordType==='text'}">
                            <!-- <i class="fas fa-eye"></i> -->
                        </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <!-- <i class="fas fa-key"></i> -->
                            <label for="password-confirm">비밀번호 확인</label>
                        </div>
                        <input v-model="signUpData.password2" 
                            id="password-confirm"
                            name="password-confirm"
                            type="password"
                            placeholder=" 비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : signUpData.passwordConfirmType==='text'}">
                            <!-- <i class="fas fa-eye"></i>  -->
                        </span>
                    </div>

                    <div class="input-wrap name-warp">
                        <div class="p-2">
                            <!-- <i class="fas fa-user"></i> -->
                            <label for="name">이름</label>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.name" 
                            id="name"
                            name="name"
                            placeholder=" 이름을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap ">
                        <div class="p-2">
                            <!-- <i class="fas fa-user"></i> -->
                            <label for="nickname">닉네임</label>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.nickname"
                            id="nickname"
                            name="nickname"
                            placeholder=" 닉네임을 입력해주세요" type="text"/>
                    </div>

                    <div class="input-wrap address-warp">
                        <div class="p-2">
                            <!-- <i class="fas fa-home"></i> -->
                            <label for="address">주소</label>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.address" 
                            id="address"
                            name="address"
                            placeholder=" 주소를 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap birthday-warp">
                        <div class="p-2">
                            <!-- <i class="fas fa-birthday-cake"></i> -->
                            <label for="birthday">생일</label>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.birthday" 
                            id="birthday"
                            name="birthday"
                            placeholder=" YYYY-MM-DD"
                            type="text"/>
                    </div>

                    
                </div>

                <div class="seeModal">
                    <!-- Button trigger modal -->
                    <button type="button" class="modalBtn" data-toggle="modal" data-target="#exampleModal">
                    약관 보기
                    </button>
                    <!-- Modal -->
                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-scrollable">
                        <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">회원가입 이용약관</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <div class="container">
                                <div class="article">제1조 (목적)<p></p></div>
                                <div class="content">
                                    본 약관은 ShallWe(쉘위) (이하 '회사')가 고도 사이트(이하 ‘사이트’)를 통하여 인터넷상에서 제공하는 서비스(이하 ‘서비스’)를 이용하는 고객 (이하 '고객')간의 권리와 의무 및 책임 등 기타 제반사항을 규정함을 목적으로 합니다.
                                </div>
                            </div>
                            <br>
                            <div class="container">
                                <div class="article">제2조 (용어의 정의)<p></p></div>
                                <div class="content">
                                    이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
                                    <br>가. '회원'이라 함은 이 약관에 동의하고 회원가입을 통하여 이용자ID(고유번호)와 비밀번호를 발급받은 자로서, 회사가 제공하는 서비스를 이용할 수 있는 이용자를 말합니다.
                                    <br>나. '이용자ID'라 함은 회원의 식별 및 서비스 이용을 위하여 회원의 신청에 따라 회사가 회원 별로 부여하는 고유한 문자와 숫자의 조합을 말합니다.
                                    <br>다. '비밀번호'라 함은 이용자ID로 식별되는 회원의 본인 여부를 검증하기 위하여 회원이 설정하여 회사에 등록한 고유의 문자와 숫자의 조합을 말합니다.
                                    <br>라. ‘로그인’이라 함은 이용자ID와 비밀번호를 통하여 서비스 신청 및 사용 중 서비스의 세부정보를 확인할 수 있는 행위를 말합니다.
                                    <br>마. '탈퇴'라 함은 회원이 서비스 이용을 해지하는 것을 말합니다.
                                    <br>바. ‘휴면ID’ 라 함은 회사에서 정한 일정 기간 동안 사이트 접속 및 미 로그인 시 고객의 정보 악용방지를 위하여 보호되는 계정입니다.
                                    <br>이 약관에서 사용하는 용어 중 제1항에서 정하지 아니한 것은 관계 법령 및 서비스 별 안내에서 정하는 바에 따르며, 그 외에는 일반 관례에 따릅니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제3조 (이용약관의 효력 및 개정)<p></p></div>
                                <div class="content">
                                1. 이 약관은 사이트를 통해 온라인으로 공시하고 회원의 동의로 효력을 발생합니다. 본 약관의 공시는 사이트에 게시하는 방법으로 합니다.
                                <br>2. 회사는 합리적인 사유가 발생할 경우 관련 법령에 위배되지 않는 범위 내에서 약관을 개정할 수 있습니다. 개정된 약관은 사이트를 통해 공지하거나 기타의 방법으로 회원에게 공지함으로써 그 효력이 발생하며, 이용자의 권리 또는 의무에 관한 변경은 최소한 7일 전에 공지합니다.
                                <br>3. 변경된 약관에 대한 정보를 알지 못해 발생하는 회원의 피해는 회사에서 책임지지 않습니다.
                                <br>4. 회원은 변경된 약관에 동의하지 않을 경우 서비스 이용을 중단하고 회원 탈퇴를 요청할 수 있습니다. 단, 이의가 있음에도 불구하고 제 2항에서 정한 바에 따른 회사의 고지가 있은 후 30일 이내에 이용계약을 하지 않은 회원은 변경된 약관에 동의한 것으로 간주합니다. 
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제4조 (약관외 준칙)<p></p></div>
                                <div class="content">
                                회사는 필요한 경우 기타 유ㆍ무료 서비스 내의 운영원칙 또는 개별약관(이하 '서비스 별 약관'이라 합니다)를 정할 수 있으며, 본 약관과 서비스 별 약관의 내용이 상충되는 경우에는 서비스 별 약관의 내용을 우선하여 적용합니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제5조 (회원 가입)<p></p></div>
                                <div class="content">
                                1. 이 약관의 동의는 회원가입 당시 절차 상의 이용약관 및 개인정보처리방침에 동의한다는 의사표시와 함께 회사가 요청하는 정보를 제공하여 회원가입 신청을 완료함으로써 이루어집니다. 
                                <br>2. 회원으로 가입하여 서비스를 이용하고자 하는 이용자는 회사에서 요청하는 제반 정보(개인정보처리방침 제 1항 ‘수집하는 개인정보의 항목’)를 제공하여야 합니다.
                                <br>3. 회원은 반드시 회원 본인의 정보를 제공하여야만 서비스를 이용할 수 있으며, 타인의 정보를 도용하거나 허위의 정보를 등록하는 등 본인의 정확한 정보를 등록하지 않은 회원은 서비스 이용과 관련하여 아무런 권리를 주장할 수 없으며, 관계 법령에 따라 처벌 받을 수 있습니다. 또한 제 3자에게 야기한 손해를 배상할 책임이 있으며, 회사는 허위의 정보를 기재한 회원의 서비스 이용 자격을 제한할 수 있습니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제6조 (개인정보의 보호 및 사용)<p></p></div>
                                <div class="content">
                                1. 회사는 관계 법령이 정하는 바에 따라 회원의 개인정보를 보호하기 위해 노력합니다. 개인정보의 보호 및 사용에 관해서는 관계 법령 및 회사의 개인정보처리방침에 정한 바에 의합니다. 단, 회원은 이용자ID 및 비밀번호 등이 타인에게 노출되지 않도록 철저히 관리해야 하며 회사는 회원의 귀책사유로 인해 노출된 정보에 대해서 책임을 지지 않습니다.
                                <br>2. 회사는 고객이 제공하는 개인정보를 본 서비스 이외의 목적을 위하여 사용할 수 없습니다.
                                <br>3. 회사는 고객이 제공한 개인정보를 고객의 사전 동의 없이 제 3자에게 제공할 수 없습니다. 단, 다음 각 호에 해당하는 경우에는 예외로 합니다.
                                <br>가. 도메인이름 검색서비스를 제공하는 경우
                                <br>나. 전기통신기본법 등 관계법령에 의하여 국가기관의 요청이나 범죄에 대한 수사상의 목적이 있거나 정보통신윤리위원회의 요청이 있는 경우
                                <br>다. 통계작성, 홍보자료, 시장조사를 위하여 필요한 경우로서 특정 고객임을 식별할 수 없는 형태로 제공되는 경우
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제7조 (회원에 대한 통지 및 정보제공)<p></p></div>
                                <div class="content">
                                1. 회사가 회원에 대하여 통지하는 경우 회원이 회사에 등록한 전자우편 주소나 휴대전화번호, 유선전화번호로 할 수 있습니다.
                                <br>2. 회사는 불특정다수 회원에게 통지를 해야 할 경우 고도 공지게시판을 통해 7일 이상 게시함으로써 개별 통지에 갈음할 수 있습니다.
                                <br>3. 회사는 회원에게 서비스 이용에 필요가 있다고 인정되는 각종 정보에 대해서 전자우편이나 서신, 우편, SMS, 전화 등의 방법으로 회원에게 제공할 수 있습니다.
                                <br>4. 회사는 서비스 개선 및 회원 대상의 서비스 소개 등의 목적으로 회원의 동의 하에 관련 법령에 따라 추가적인 개인 정보를 수집할 수 있습니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제8조(서비스의 이용 시간 및 중단)<p></p></div>
                                <div class="content">
                                1. 서비스 이용은 회사의 업무상 또는 기술상 특별한 지장이 없는 한 연중무휴, 1일 24시간 운영을 원칙으로 합니다. 단, 회사는 시스템 정기점검, 증설 및 교체를 위해 회사가 정한 날이나 시간에 서비스를 일시 중단할 수 있으며, 예정되어 있는 작업으로 인한 서비스 일시 중단은 고도를 통해 사전에 사이트를 통하여 공지합니다.
                                <br>2. 회사는 긴급한 시스템 점검, 증설 및 교체, 설비의 장애, 서비스 이용의 폭주, 국가비상사태, 정전 등 부득이한 사유가 발생한 경우 사전 예고 없이 일시적으로 서비스의 전부 또는 일부를 중단할 수 있습니다.
                                <br>3. 회사는 서비스 개편 등 서비스 운영 상 필요한 경우 전부 또는 일부 서비스의 제공을 중단할 수 있으며 회원에게 사전 예고 또는 사후 통보 합니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제9조 (회사의 의무)<p></p></div>
                                <div class="content">
                                1. 회사는 본 약관이 정하는 바에 따라 지속적이고 안정적인 서비스를 제공하기 위해서 노력합니다.
                                <br>2. 회사는 회원의 개인정보 보호를 위해 보안 시스템을 구축하며 개인정보 처리방침을 공시하고 준수합니다.
                                <br>3. 회사는 공정하고 건전한 운영을 위해 최선을 다하고 지속적인 연구개발을 통하여 양질의 서비스를 제공함으로써 고객만족을 극대화하여 인터넷 사업 발전에 기여합니다.
                                <br>4. 회사는 회원으로부터 제기되는 의견이나 불편사항이 정당하다고 객관적으로 인정될 경우에는 적절한 절차를 통해 즉시 처리하여야 합니다. 다만, 신속한 처리가 곤란한 경우는 회원에게 그 사유와 처리일정을 통보하여야 합니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제10조 (회원의 의무)<p></p></div>
                                <div class="content">
                                1. 회원은 회원가입 신청 또는 회원정보 변경 시 모든 사항을 사실에 근거하여 본인의 실제 정보로 작성하여야 하며, 허위 또는 타인의 정보를 등록할 경우 이와 관련된 모든 권리를 주장할 수 없습니다.
                                <br>2. 회원은 본 약관에서 규정하는 사항과 기타 회사가 정한 제반 규정, 공지사항 등 회사가 공지하는 사항 및 관계 법령을 준수하여야 하며, 기타 회사의 업무에 방해가 되는 행위, 회사의 명예를 손상시키는 행위, 타인에게 피해를 주는 행위를 해서는 안됩니다.
                                <br>3. 회원은 주소, 연락처, 전자우편 주소 등 회원의 이용정보가 변경된 경우에 해당 절차를 거쳐 이를 회사에 즉시 알려야 합니다.
                                <br>4. 회원은 회원ID, 비밀번호 등이 타인에게 노출되지 않도록 철저한 관리 책임이 있습니다.
                                <br>5. 회원은 회원ID, 비밀번호 등이 도난 당하거나 제 3자가 사용하고 있음을 인지한 경우에는 즉시 회사에 통보하고 회사의 안내가 있는 경우에는 그에 따라야 합니다.
                                <br>6. 회사는 회원의 상기 제1항, 제 2항, 제 3항, 제 4항, 제 5항을 위반하여 회원에게 발생한 손해에 대하여 어떠한 책임도 부담하지 않습니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제11조 (회원 탈퇴 및 자격 상실)<p></p></div>
                                <div class="content">
                                1. 회원은 회사에 언제든지 회원 탈퇴를 요청할 수 있으며 회사는 요청을 받은 즉시 해당 회원의 회원 탈퇴를 위한 절차를 밟아 고도 개인정보처리방침에 따라 회원 등록을 말소합니다.
                                <br>2. 회사의 모든 서비스에서 이용중인 서비스의 기간이 남아있는 회원이 탈퇴 요청하였을 경우 회원탈퇴로 인한 서비스의 중지 또는 피해는 회원탈퇴 이용자에게 있습니다.
                                <br>3. 회원이 서비스 이용에 있어서 본 약관 제 10조 내용을 위반하거나, 다음 각 호의 사유에 해당하는 경우 회사는 직권으로 회원자격 상실 및 회원탈퇴의 조치를 할 수 있습니다.
                                <br>가. 다른 사람의 명의를 사용하여 가입 신청한 경우
                                <br>나. 신청 시 필수 작성 사항을 허위로 기재한 경우
                                <br>다. 관계법령의 위반을 목적으로 신청하거나 그러한 행위를 하는 경우
                                <br>라. 사회의 안녕질서 또는 미풍양속을 저해할 목적으로 신청하거나 그러한 행위를 하는 경우
                                <br>마. 다른 사람의 회사 서비스 이용을 방해하거나 그 정보를 도용하는 등 전자거래질서를 위협하는 경우
                                <br>바. 관계 법령 위배와 본 약관이 금지하는 행위를 하는 회원 경우
                                <br>4. 회사가 직권으로 회원탈퇴 처리를 하고자 하는 경우에는 말소 전에 회원에게 소명의 기회를 부여합니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제12조 (휴면ID 관리)<p></p></div>
                                <div class="content">
                                1. 회사는 서비스 미 이용 및 1년 이상 사이트 로그인을 하지 않은 경우 해당 이용자ID 및 개인정보를 휴면ID로 별도 관리합니다.
                                <br>2. 회사는 휴면ID 대상 회원에게 전환 30일 전에 고지의 의무를 다 해야 하며, 휴면ID를 이용하여 사이트 로그인 시도 시 회원가입 당시 요청한 제반 정보 확인 후 바로 이용자 ID로 전환이 이루어집니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제13조 (손해배상)<p></p></div>
                                <div class="content">
                                1. 회사는 서비스에서 무료로 제공하는 서비스의 이용과 관련하여 개인정보보호정책에서 정하는 내용에 해당하지 않는 사항에 대하여 어떠한 손해도 책임을 지지 않습니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제14조 (면책조항)<p></p></div>
                                <div class="content">
                                1. 회사는 천재지변, 전쟁, 기간통신사업자의 서비스 중지 및 기타 이에 준하는 불가항력으로 인하여 서비스를 제공할 수 없는 경우에는 서비스 제공에 대한 책임 지지 않습니다.
                                <br>2. 회사는 서비스용 설비의 보수, 교체, 정기점검, 공사 등 부득이한 사유로 발생한 손해에 대한 책임이 면제됩니다.
                                <br>3. 회사는 회원이 서비스에 게재한 정보, 자료, 사실의 정확성, 신뢰성 등 그 내용에 관하여는 어떠한 책임을 부담하지 아니하고, 회원은 자기의 책임아래 서비스를 이용하며, 서비스를 이용하여 게시 또는 전송한 자료 등에 관하여 손해가 발생하거나 자료의 취사선택, 기타 서비스 이용과 관련하여 어떠한 불이익이 발생하더라도 이에 대한 모든 책임은 회원에게 있습니다.
                                <br>4. 회사가 제공하는 정보와 자료는 거래의 목적으로 이용될 수 없습니다. 따라서 본 서비스의 정보와 자료 등에 근거한 거래는 전적으로 회원자신의 책임과 판단아래 수행되는 것이며, 회사는 회원이 서비스의 이용과 관련하여 기대하는 이익에 관하여 책임을 부담하지 않습니다.
                                <br>5. 회사는 회원이 서비스를 이용하여 기대하는 수익을 얻지 못하거나 상실한 것에 대하여 책임을 지지 않으며, 서비스를 이용하면서 얻은 자료로 인한 손해에 대하여 책임을 지지 않습니다.
                                <br>6. 회사는 회원의 게시물을 등록 전에 사전심사 하거나 상시적으로 게시물의 내용을 확인 또는 검토하여야 할 의무가 없으며, 그 결과에 대한 책임을 지지 아니합니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제15조 (약관의 해석 및 관할법원)<p></p></div>
                                <div class="content">
                                1. 본 약관에 명시되지 않은 사항은 관계 법령과 상관례에 따릅니다.
                                <br>2. 회사의 개별 유ㆍ무료 서비스 이용 회원의 경우 당해 서비스와 관련하여서는 회사가 별도로 정한 약관 및 정책에 따릅니다.
                                <br>3. 회원과 회사 사이에 발생한 분쟁에 대해 소송이 제기되는 경우 관할 법원은 서울중앙지방법원으로 합니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                <div class="article">제16조 (재판관할 및 준거법)<p></p></div>
                                <div class="content">
                                1. 이 약관에 명시되지 않은 사항은 전기통신사업법 등 대한민국의 관계법령과 상관습에 따릅니다.
                                <br>2. 회사의 정액 서비스 회원 및 기타 유료 서비스 이용 회원의 경우 당해 서비스와 관련하여서는 회사가 별도로 정한 약관 및 정책에 따릅니다.
                                <br>3. 이 약관 및 서비스의 이용과 관련된 분쟁에 관한 소송은 회사의 본사 소재지를 관할하는 법원에 제기합니다.
                                </div>
                            </div>
                            <br>

                            <div class="container">
                                부칙 (시행일) <br>
                                본 약관은 2020년 8월 10일부터 적용합니다.
                            </div>


                        </div>
                        <div class="modal-footer"> 
                            <div class="agree">
                                <label for="agreeType" class="agreeText">약관에 동의합니다.</label>
                                <input type="checkbox" id="agreeType" v-model="isTerm" @click="termCheck">
                            </div>
                            <button type="button" class="btn btn-secondary agreeBtn" data-dismiss="modal">Close</button>
                        </div>
                        </div>
                    </div>
                    </div>
                    </div>


                <hr class="line">
                <button @click="sendEmail(signUpData)" v-if="!isSended" class="btn">
                    <span>
                        이메일 인증
                    </span>
                </button>
                <button @click="signUp(signUpData)" v-if="isSended" class="btn">
                    <span>
                        가입하기
                    </span>
                </button>
                <input type="text" v-if="isSended" v-model="signUpData.signUpDataForSend.authNumber" placeholder="메일로 받으신 인증번호를 입력해주세요">
            </div>
        </div> 
    </div>
</template>


<script>
import axios from "axios"
import {mapActions, mapState, mapMutations} from 'vuex'
const BACK_URL = "http://127.0.0.1:8080"

    export default {
        name: "Signup",
        components: {
        },
        data: () => {
            return {
                signUpData:{
                    signUpDataForSend:{
                        address:'',
                        birthday:'',
                        email: '',
                        id: '',
                        name:'', 
                        nickname: '',
                        password:'',
                        authNumber:'',
                    },
                    password2:''       
                },
                isTerm: false,
            }
        },
        methods: {
            ...mapActions(["signUp","sendEmail"]),
            ...mapMutations(["termCheck"])
        },
        computed:{
            ...mapState(["isSended"])
        }
    }

</script>

<style scoped>
.form-wrap{
    justify-content: center;
    align-items: center;
    margin-left: 34%;
    margin-top: 30px;
    margin-bottom: 20px;
}
.input-wrap{
    display: flex;
    /* justify-content: flex-start; */
    margin-bottom: 10px;
}
.line{
    width:40%;
    margin-bottom: 20px;
    height:3px;
    border-color: black;
}
label{
    width: 150px;
    text-align: left;
    font-weight: 550;
    color: black;
}
input{
    width: 30%;
    height: 40px;
    border: 1px solid grey;
    border-radius: 5px;
    opacity: 0.5;
}
.submitButton{
    color: rgb(202, 51, 195);
    background: transparent;
    border: 3px solid plum;
    border-radius: 5px;
    -webkit-transition: all .5s ease;
    transition: all .5s ease;
    -webkit-transform: translate(0, 0);
    transform: translate(0, 0);
    width:25%;
    height: 50px;
    margin-top: 10px;
}
.submitButton:hover{
    background-color: rgb(202, 51, 195);
    color: white ;
    text-decoration: solid;
    border: 3px solid rgb(202, 51, 195);
    -webkit-transition: all .35s ease;
    transition: all .35s ease;
    /* >a
        color aqur-bg
        -webkit-transition all .35s ease
        transition all .35s ease */
}
/* .seeModal{
    justify-content: center;
    justify-self: center;
} */
.modalBtn{
    background-color: rgb(202, 51, 195);
    color: white;
    border-radius: 0.4em;
    border: 1px solid rgb(202, 51, 195);
    width: 90px;
    height:35px;
    /* justify-content: center;
    justify-self: center; */
    /* margin-right:400px; */
}
.article{
    text-align: left;
    font-weight: 550;
}
.content{
    text-align: left;
}
.container{
    border-radius: 0.5em;
    border:1px solid rgb(139, 137, 137);
    padding: 10px 5px;
}
.agree{
    display: flex;
    justify-content: center;
    justify-self: center;
}
.agreeText{
    width:100%;
}
.agreeBtn{
    margin-left:85px;
}
input[type="checkbox"]{
    width: 25px;
    height: 25px;
    margin-left:2.5px;
}
</style>