<template>
  <v-container fluid tile>
    <v-card outlined elevation="2" class="py-0">
      <v-row justify="center">
        <v-container class="my-4">
          <v-row>
            <v-col cols="12"><v-card-title>1. 당사자 표시<v-badge class="pb-1 pl-1" inline dot color="red"/></v-card-title></v-col>
            <v-col md="2"><v-text-field disabled label="갑 (사용자)"/></v-col>
            <v-col md="5"><v-text-field color="purple" label="사업장명" placeholder="(주)쓰리웨어"/></v-col>
            <v-col md="5"><v-text-field color="purple" label="대표이사" placeholder="이재헌"/></v-col>
            <v-col md="2"><v-text-field disabled label="을 (근로자)"/></v-col>
            <v-col md="5"><v-text-field color="purple" label="성명" placeholder="홍길동"/></v-col>
            <v-col md="5"><v-text-field color="purple" label="입사일" placeholder="2020-04-01"/></v-col>

            <v-col cols="12"><v-card-title>2. 근로계약기간<v-badge class="pb-1 pl-1" inline dot color="red"/></v-card-title></v-col>
            <v-col md="4"><v-text-field disabled label="본 계약서상 연봉적용 기간은"/></v-col>
            <v-col md="4"><v-text-field color="purple" label="계약기간" placeholder="2020-04-01"/></v-col>
            <v-col md="4"><v-text-field disabled label="까지로 한다."/></v-col>

            <v-col cols="12"><v-card-title>3. 근로시간 및 휴게시간</v-card-title></v-col>
            <v-col md="4"><v-text-field disabled label="평일"/></v-col>
            <v-col md="4"><v-text-field color="purple" label="시업 및 종업시각" value="09:00 ~ 18:00"/></v-col>
            <v-col md="4"><v-text-field color="purple" label="휴게시간" value="12:00 ~ 13:00"/></v-col>
            <v-col md="4"><v-text-field disabled label="토요일"/></v-col>
            <v-col md="4"><v-text-field color="purple" label="시업 및 종업시각" value="무급휴무일"/></v-col>
            <v-col md="4"><v-text-field color="purple" label="휴게시간" value="무급휴무일"/></v-col>
            <v-col md="4"><v-text-field disabled label="일요일"/></v-col>
            <v-col md="4"><v-text-field color="purple" label="시업 및 종업시각" value="유급휴일(주휴일)"/></v-col>
            <v-col md="4"><v-text-field color="purple" label="휴게시간" value="유급휴일(주휴일)"/></v-col>
            <v-col cols="12"><v-card-title>4. 근무내용과 근무장소</v-card-title></v-col>

            <v-col md="6"><v-text-field color="purple" label="근무내용" value="영업 본부 사장"/></v-col>
            <v-col md="6"><v-text-field color="purple" label="근무장소" value="갑의 사업장 내, 단 업무상 필요한 경우 갑은 을의 근무장소를 변경할 수 있다."/></v-col>

            <v-col cols="12"><v-card-title>5. 임금내역<v-badge class="pb-1 pl-1" inline dot color="red"/></v-card-title></v-col>
            <v-col cols="12"><v-card-subtitle>임금내역</v-card-subtitle></v-col>
            <v-col md="3"><v-text-field color="purple" label="기본급(연간)" placeholder="70,000,000"/></v-col>
            <v-col md="3"><v-text-field color="purple" label="제수당(연간)" value="0"/></v-col>
            <v-col md="3"><v-text-field color="purple" label="상여금" value="0"/></v-col>
            <v-col md="3"><v-text-field color="purple" label="연봉(총액)" placeholder="70,000,000"/></v-col>
            <v-col cols="12"><v-card-subtitle>임금 구성 항목 및 계산 방법</v-card-subtitle></v-col>
            <v-col md="2"><v-text-field color="purple" label="기본급" placeholder="70,000,000"/></v-col>
            <v-col md="2"><v-text-field color="purple" label="연장근로수당" value="0"/></v-col>
            <v-col md="2"><v-text-field color="purple" label="야간근로수당" value="0"/></v-col>
            <v-col md="2"><v-text-field color="purple" label="휴일근로수당" value="0"/></v-col>
            <v-col md="2"><v-text-field color="purple" label="차량유지비" value="0"/></v-col>
            <v-col md="2"><v-text-field color="purple" label="식대" value="0"/></v-col>

            <v-col cols="12"><v-card-title>6. 퇴직급여</v-card-title></v-col>
            <v-col md="3"><v-text-field disabled color="purple" label="취업규칙에 준함" /></v-col>

            <v-col cols="12"><v-card-title>7. 휴가 및 휴일<v-badge class="pb-1 pl-1" inline dot color="red"/></v-card-title></v-col>
            <v-col md="9"><v-textarea color="purple" rows=8 clearable clear-icon="mdi-close-circle" label="약관" v-model="this.holidayTerms"/></v-col>
            <v-col md="3">
              <div class="row">
                <div class="col-12 mt-2"><VueSignaturePad id="signature" width=268px height=152px ref="signaturePad" :options="options"/></div>
              </div>
              <div class="row">
                <div class="mt-4 ml-4">
                  <v-btn color="purple" small outlined class="btn btn-outline-secondary" @click="save, savesnackbar=true">저장</v-btn>
                  <v-snackbar color="primary" text v-model="savesnackbar" :timeout="timeout">
                  {{ savetext }}
                    <template v-slot:action="{ attrs }">
                      <v-btn color="primary" text v-bind="attrs" @click="savesnackbar = false">
                        닫기
                      </v-btn>
                    </template>
                  </v-snackbar>
                </div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="del, delsnackbar=true">삭제</v-btn>
                <v-snackbar color="primary" text v-model="delsnackbar" :timeout="timeout">
                {{ deltext }}
                  <template v-slot:action="{ attrs }">
                    <v-btn color="primary" text v-bind="attrs" @click="delsnackbar = false">
                      닫기
                    </v-btn>
                  </template>
                </v-snackbar>
                </div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="undo">이전</v-btn></div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="color">색상</v-btn></div>
              </div>
            </v-col>

            <v-col cols="12"><v-card-title>8. 손해배상, 중도퇴직, 자원의 귀속, 경업금지의무 등</v-card-title></v-col>
            <v-col md="3"><v-text-field disabled color="purple" label="취업규칙에 준함" /></v-col>

            <v-col cols="12"><v-card-title>9. 기타<v-badge class="pb-1 pl-1" inline dot color="red"/></v-card-title></v-col>
            <v-col md="9"><v-textarea color="purple" clearable clear-icon="mdi-close-circle" label="약관" v-model="this.etcTerms"/></v-col>
            <v-col md="3">
              <div class="row">
                <div class="col-12 mt-2"><VueSignaturePad id="signature"  width=268px height=152px ref="signaturePad" :options="options"/></div>
              </div>
              <div class="row">
                <div class="mt-4 ml-4">
                  <v-btn color="purple" small outlined class="btn btn-outline-secondary" @click="save, savesnackbar=true">저장</v-btn>
                  <v-snackbar color="primary" text v-model="savesnackbar" :timeout="timeout">
                  {{ savetext }}
                    <template v-slot:action="{ attrs }">
                      <v-btn color="primary" text v-bind="attrs" @click="savesnackbar = false">
                        닫기
                      </v-btn>
                    </template>
                  </v-snackbar>
                </div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="del, delsnackbar=true">삭제</v-btn>
                <v-snackbar color="primary" text v-model="delsnackbar" :timeout="timeout">
                {{ deltext }}
                  <template v-slot:action="{ attrs }">
                    <v-btn color="primary" text v-bind="attrs" @click="delsnackbar = false">
                      닫기
                    </v-btn>
                  </template>
                </v-snackbar>
                </div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="undo">이전</v-btn></div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="color">색상</v-btn></div>
              </div>
            </v-col>

            <v-col cols="12"><v-card-title>10. 특약</v-card-title></v-col>
            <v-col md="12"><v-textarea color="purple" rows=2 clearable clear-icon="mdi-close-circle" label="약관" v-model="this.specialTerms"/></v-col>
          </v-row>
        </v-container>
      </v-row>
    </v-card>
    <v-card outlined elevation="2" class="my-5 py-5">
      <v-row justify="center">
        <v-container class="my-4 mb-15">
          <v-row>
            <v-col cols="12"><v-card-title>* 서명<v-badge class="pb-1 pl-1" inline dot color="red"/></v-card-title></v-col>
            <v-col md="4"><v-text-field color="purple" label="작성일" placeholder="2020-04-01"/></v-col><v-col md="8"></v-col>
            <v-col md="2"><v-card-title>갑 (사용자)</v-card-title></v-col><v-col md="10"></v-col>
            <v-col md="4"><v-text-field color="purple" label="사업장명" placeholder="(주)쓰리웨어"/></v-col>
            <v-col md="4"><v-text-field color="purple" label="대표이사" placeholder="이재헌"/></v-col>
            <v-col md="3">
              <div class="row">
                <div class="col-12 mt-2"><VueSignaturePad id="signature" width=268px height=152px ref="signaturePad" :options="options"/></div>
              </div>
              <div class="row">
                <div class="mt-4 ml-4">
                  <v-btn color="purple" small outlined class="btn btn-outline-secondary" @click="save, savesnackbar=true">저장</v-btn>
                  <v-snackbar color="primary" text v-model="savesnackbar" :timeout="timeout">
                  {{ savetext }}
                    <template v-slot:action="{ attrs }">
                      <v-btn color="primary" text v-bind="attrs" @click="savesnackbar = false">
                        닫기
                      </v-btn>
                    </template>
                  </v-snackbar>
                </div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="del, delsnackbar=true">삭제</v-btn>
                <v-snackbar color="primary" text v-model="delsnackbar" :timeout="timeout">
                {{ deltext }}
                  <template v-slot:action="{ attrs }">
                    <v-btn color="primary" text v-bind="attrs" @click="delsnackbar = false">
                      닫기
                    </v-btn>
                  </template>
                </v-snackbar>
                </div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="undo">이전</v-btn></div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="color">색상</v-btn></div>
              </div>
            </v-col>
            <v-col md="2"><v-card-title>을 (근로자)</v-card-title></v-col><v-col md="10"></v-col>
            <v-col md="4"><v-text-field color="purple" label="생년월일" placeholder="1980-01-01"/></v-col>
            <v-col md="4"><v-text-field color="purple" label="성명" placeholder="홍길동"/></v-col>
            <v-col md="3">
              <div class="row">
                <div class="col-12 mt-2"><VueSignaturePad id="signature" width=268px height=152px ref="signaturePad" :options="options"/></div>
              </div>
              <div class="row">
                <div class="mt-4 ml-4">
                  <v-btn color="purple" small outlined class="btn btn-outline-secondary" @click="save, savesnackbar=true">저장</v-btn>
                  <v-snackbar color="primary" text v-model="savesnackbar" :timeout="timeout">
                  {{ savetext }}
                    <template v-slot:action="{ attrs }">
                      <v-btn color="primary" text v-bind="attrs" @click="savesnackbar = false">
                        닫기
                      </v-btn>
                    </template>
                  </v-snackbar>
                </div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="del, delsnackbar=true">삭제</v-btn>
                <v-snackbar color="primary" text v-model="delsnackbar" :timeout="timeout">
                {{ deltext }}
                  <template v-slot:action="{ attrs }">
                    <v-btn color="primary" text v-bind="attrs" @click="delsnackbar = false">
                      닫기
                    </v-btn>
                  </template>
                </v-snackbar>
                </div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="undo">이전</v-btn></div>
                <div class="mt-4 ml-4"><v-btn color="purple" small outlined class="btn btn-outline-primary" @click="color">색상</v-btn></div>
              </div>
            </v-col>
          </v-row>
        </v-container>
      </v-row>
    </v-card>
  </v-container>
</template>

<script lang="ts">
import 'codemirror/lib/codemirror.css'
import '@toast-ui/editor/dist/toastui-editor.css'
import '@toast-ui/editor/dist/toastui-editor-viewer.css'

import { Component, Vue } from 'vue-property-decorator'
import { Editor } from '@toast-ui/vue-editor'
import VueSignaturePad from 'vue-signature-pad'

Vue.use(VueSignaturePad)

@Component({
  components: {
    Editor
  },
  data: () => ({
    savesnackbar: false,
    delsnackbar: false,
    savetext: '저장되었습니다.',
    deltext: '삭제되었습니다.',
    timeout: 1500
  })
})

export default class Base2 extends Vue {
  holidayTerms = `1) "갑"은 "을"에게 1주 동안 개근 시 매주 일요일, 매년 근로자의 날인 5월 1일에 각각 1일의 유급휴일을 부여한다.

2) "갑"은 "을"에게 연차유급휴가를 부여하고, 서면합의로 "갑"의 내부규정에 명시한 유급휴일이 아닌 공휴일, 병가, 결근일, 하계휴가, P/J휴가 등에 대체하여 사용하는 것을 원칙으로 한다.

3) "갑"은 업무상 부득이한 사정이 있는 경우 휴일을 다른 날로 대체할 수 있다.

4) "갑"은 "을"에게 연장근로, 야간근로, 휴일근로에 대하여 임금 지급에 갈음하여 휴가를 줄 수 있다.`
  etcTerms = `1) 본 계약서에 없는 사항은 노동관계법령 및 "갑"의 취업규칙에 따르기로 한다.

2) 상기 인적사항 및 연봉에 관한 사항은 절대 기밀을 유지하며 이를 위반하여 타인에게 누설할 시 징계 사유가 된다.

3) 이 계약서는 회사 그룹웨어를 통하여 "을"이 서명하여 송부한 것을 원본으로 한다.`
  specialTerms = `"을"은 상기 포괄임금산정제, 연차유급휴가대체, 휴일대체, 보상휴가제 등의 시행에 대해 "갑"으로부터 충분히 설명을 들었고, 이의 시행에 대해 동의하며,
그 외 본 계약서에 명시된 모든 내용을 완벽히 숙지하였으며, 본 근로계약을 자의적으로 체결함을 확인합니다.`
}
</script>
