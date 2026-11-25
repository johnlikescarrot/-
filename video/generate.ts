// Node 22 TypeScript source: builds the SVG storyboards, original stereo score and final MP4.
// Inputs are the checked-in static-analysis results. No APK execution, API access or network IO.
import fs from 'node:fs';
import path from 'node:path';
import { spawnSync } from 'node:child_process';
import { fileURLToPath } from 'node:url';

const ROOT = path.resolve(path.dirname(fileURLToPath(import.meta.url)), '..');
const OUT = path.join(ROOT, 'deliverables');
const FRAMES = path.join(OUT, 'film-frames');
const TMP = path.join(OUT, '.film-build');
const findings = JSON.parse(fs.readFileSync(path.join(ROOT, 'research/output/findings.json'), 'utf8'));
const base = findings.apk_analyses.find((a: any) => a.apk === 'vn.com.vng.zalopay.apk');
const arm = findings.apk_analyses.find((a: any) => a.apk === 'config.armeabi_v7a.apk');
if (!base || !arm) throw new Error('Required base and ABI-split analysis is missing; run research/analyze_apk.py first.');

const W=1920, H=1080, FPS=24, SCENE=11, FADE=0.72;
const esc=(s: string)=>s.replaceAll('&','&amp;').replaceAll('<','&lt;').replaceAll('>','&gt;').replaceAll('"','&quot;');
const n=(v: number)=>new Intl.NumberFormat('vi-VN').format(v);
const colors={ink:'#08131f',panel:'#102232',teal:'#50e0c2',blue:'#6ba7ff',amber:'#ffca77',coral:'#ff7f79',muted:'#9aafc2',white:'#f5f8fc'};

function pill(x:number,y:number,text:string,color=colors.teal){return `<g><rect x="${x}" y="${y-32}" width="${Math.max(150,text.length*17+48)}" height="48" rx="24" fill="${color}" fill-opacity=".12" stroke="${color}" stroke-opacity=".4"/><text x="${x+22}" y="${y}" class="pill" fill="${color}">${esc(text)}</text></g>`;}
function ring(cx:number,cy:number,r:number,c:string,stroke=3){return `<circle cx="${cx}" cy="${cy}" r="${r}" fill="none" stroke="${c}" stroke-width="${stroke}" stroke-opacity=".72"/>`;}
function dots(cx:number,cy:number,r:number,count:number,color:string){let out='';for(let i=0;i<count;i++){const a=(Math.PI*2*i/count)-Math.PI/2;const x=cx+Math.cos(a)*r,y=cy+Math.sin(a)*r;out+=`<circle cx="${x.toFixed(1)}" cy="${y.toFixed(1)}" r="6" fill="${color}"/>`;}return out;}
function metric(x:number,y:number,value:string,label:string,color=colors.teal){return `<g><text x="${x}" y="${y}" class="metric" fill="${color}">${esc(value)}</text><text x="${x}" y="${y+46}" class="small">${esc(label)}</text></g>`;}

const scenes: Array<{kicker:string,title:string[],sub:string,visual:string,foot:string}>=[
 {kicker:'STATIC REVERSE ENGINEERING · ZALOPAY 11.17.0',title:['GIẢI PHẪU','MỘT FINTECH APK'],sub:'Một cuộc khảo sát có thể tái lập — chỉ đọc, ngoại tuyến, dựa trên bằng chứng đóng gói.',visual:`${ring(1430,560,260,colors.teal,2)}${ring(1430,560,190,colors.blue,2)}${ring(1430,560,115,colors.amber,2)}<circle cx="1430" cy="560" r="34" fill="${colors.teal}"/>${dots(1430,560,260,9,colors.teal)}${dots(1430,560,190,6,colors.blue)}<text x="1430" y="548" text-anchor="middle" class="micro dark">XAPK</text><text x="1430" y="582" text-anchor="middle" class="micro dark">11.17.0</text><path d="M1110 560h-115v-142h-90M1690 560h105v145h70" fill="none" stroke="${colors.blue}" stroke-width="2" stroke-dasharray="8 12"/>${pill(850,392,'TĨNH · KHÔNG CHẠY APK',colors.amber)}${pill(1330,742,'6472 MỤC ĐƯỢC LẬP CHỈ MỤC',colors.blue)}`,foot:'Phạm vi: đúng gói APKPure được cung cấp; không suy diễn hành vi ở thời gian chạy.'},
 {kicker:'01 / ĐÓNG GÓI & PHẠM VI',title:['MỘT TỆP ZIP.','NHIỀU LỚP BÊN TRONG.'],sub:'ZIP chứa một XAPK; XAPK lại gói ứng dụng cơ sở, bốn split cấu hình, biểu tượng và manifest.',visual:`<rect x="1130" y="258" width="590" height="520" rx="26" fill="${colors.panel}" stroke="${colors.blue}" stroke-width="2"/><text x="1180" y="322" class="micro" fill="${colors.blue}">XAPK · 105,608,128 BYTES</text><rect x="1200" y="367" width="450" height="102" rx="16" fill="#18384a"/><text x="1240" y="410" class="label">base APK</text><text x="1240" y="447" class="small">79.4 MB · 6162 mục</text><rect x="1200" y="491" width="450" height="58" rx="14" fill="#172c3b"/><text x="1240" y="530" class="small">4 split APK · ABI / ngôn ngữ / density</text><rect x="1200" y="570" width="450" height="58" rx="14" fill="#172c3b"/><text x="1240" y="609" class="small">icon.png + manifest.json</text><path d="M1425 650v52" stroke="${colors.teal}" stroke-width="3"/><text x="1425" y="748" text-anchor="middle" class="label" fill="${colors.teal}">6472 TỆP CON · SHA-256 MỖI TỆP</text>${pill(1170,828,'HASH + KÍCH THƯỚC + CRC32',colors.teal)}`,foot:'Số lượng bao gồm nội dung bên trong từng APK split; không chỉ là danh sách ZIP ngoài.'},
 {kicker:'02 / DEX & CẤU TRÚC MÃ',title:['KHÔNG CÓ','“MỘT FILE DEX”.'],sub:'Hai mươi DEX, gồm một DEX nhúng trong assets; bytecode được lập chỉ mục toàn bộ.',visual:`${metric(1170,365,'20','DEX ĐƯỢC NHẬN DIỆN',colors.teal)}${metric(1510,365,n(base.dex.class_count),'LỚP',colors.blue)}${metric(1170,535,n(base.dex.method_count),'PHƯƠNG THỨC',colors.amber)}${metric(1510,535,n(base.dex.string_count),'CHUỖI',colors.coral)}<g transform="translate(1175 665)">${Array.from({length:20},(_,i)=>`<rect x="${(i%10)*48}" y="${Math.floor(i/10)*48}" width="31" height="31" rx="7" fill="${[colors.teal,colors.blue,colors.amber,colors.coral][i%4]}" fill-opacity="${0.38+(i%4)*.12}"/>`).join('')}</g><text x="1175" y="812" class="small">19 DEX ở gốc + assets/audience_network.dex</text>`,foot:'Đếm lớp/phương thức là static metadata; tên mã/thư viện không chứng minh tính năng được kích hoạt.'},
 {kicker:'03 / MANIFEST & QUYỀN',title:['CAPABILITY','LÀ MỘT LỜI KHAI BÁO.'],sub:'Manifest nêu 49 quyền; bảng thành phần cho thấy bề mặt ứng dụng lớn và nhiều tích hợp.',visual:`${metric(1170,350,'49','QUYỀN ĐƯỢC KHAI BÁO',colors.amber)}${metric(1510,350,'267','THÀNH PHẦN',colors.teal)}<g transform="translate(1170 500)"><rect width="500" height="50" rx="12" fill="#1b3142"/><rect width="374" height="50" rx="12" fill="${colors.blue}"/><text x="22" y="35" class="small">200 Activity</text><text x="0" y="92" class="small">14 Activity exported · 34 Service · 22 Receiver · 9 Provider · 2 Alias</text><path d="M0 138h500" stroke="#2a4153" stroke-width="2"/><text x="0" y="190" class="label" fill="${colors.amber}">MẠNG · CAMERA · VỊ TRÍ · NFC</text><text x="0" y="236" class="small">Danh bạ / lịch / mic / quảng cáo / quét ứng dụng</text></g>${pill(1170,812,'KHAI BÁO ≠ ĐÃ DÙNG Ở RUNTIME',colors.coral)}`,foot:'Đối chiếu điều kiện cấp quyền theo phiên bản Android và luồng thực tế trước mọi kết luận.'},
 {kicker:'04 / ENDPOINT & CHỮ KÝ',title:['CHUỖI TĨNH','KHÔNG PHẢI LƯU LƯỢNG MẠNG.'],sub:'Trích xuất 407 URL literal; 151 hostname ứng viên từ URL — gồm endpoint, SDK, docs và placeholder.',visual:`${ring(1420,535,226,colors.teal,4)}${ring(1420,535,163,colors.blue,3)}${ring(1420,535,95,colors.amber,3)}<text x="1420" y="500" text-anchor="middle" class="metric" fill="${colors.teal}">407</text><text x="1420" y="548" text-anchor="middle" class="small">URL literal</text><text x="1420" y="632" text-anchor="middle" class="label" fill="${colors.amber}">151 host ứng viên</text>${pill(1170,818,'XÁC MINH CHỮ KÝ VỚI NGUỒN TIN CẬY',colors.blue)}<text x="1170" y="900" class="mono">SHA-256 59dd31fd…92e8cdae</text>`,foot:'Signer: CN=Vu Manh Hieu; Androguard nhận diện chữ ký v2/v3. Chưa đối chiếu khóa chính thức.'},
 {kicker:'05 / NATIVE & TÀI NGUYÊN',title:['APK LÀ MỘT','HỆ SINH THÁI ĐÓNG GÓI.'],sub:'Split ARMv7 có 27 thư viện ELF; base mang mô hình ML, web assets, font, ảnh và dữ liệu.',visual:`<g transform="translate(1155 310)"><text x="0" y="0" class="micro" fill="${colors.teal}">27 ELF · ABI armeabi-v7a</text>${arm.native_libraries.slice(0,8).map((x:any,i:number)=>`<g transform="translate(0 ${58+i*55})"><rect width="520" height="39" rx="10" fill="#172b3a"/><rect width="${180+(i*43)%280}" height="39" rx="10" fill="${[colors.teal,colors.blue,colors.amber][i%3]}" fill-opacity=".55"/><text x="18" y="27" class="mono">${esc(path.basename(x.path))}</text></g>`).join('')}<text x="0" y="545" class="small">+ 14 mô hình .tflite · ML Kit · eKYC / liveness*</text><text x="0" y="605" class="small">*nhận diện theo tên tài nguyên/thư viện, chưa kiểm thử hành vi</text></g>`,foot:'Chỉ split ARMv7 có native libraries trong bộ mẫu này; không khẳng định các ABI khác không tồn tại ngoài mẫu.'},
 {kicker:'06 / PHƯƠNG PHÁP CÓ THỂ TÁI LẬP',title:['TỪ HASH','ĐẾN CÂU HỎI CÓ THỂ KIỂM TRA.'],sub:'Artifact đi kèm gồm script, manifest đã phân tích, JSON kết quả và CSV SHA-256 theo từng tệp.',visual:`<g transform="translate(1170 325)">${[['01','Xác định chuỗi ZIP → XAPK → split'],['02','Lập inventory + SHA-256 / CRC32'],['03','Đọc manifest / DEX / ELF tĩnh'],['04','Ghi nhận giới hạn & giả thuyết'],['05','Xác minh động trong lab cô lập']].map((x:any,i:number)=>`<g transform="translate(0 ${i*94})"><circle cx="28" cy="0" r="25" fill="${[colors.teal,colors.blue,colors.amber,colors.coral,colors.teal][i]}" fill-opacity=".18" stroke="${[colors.teal,colors.blue,colors.amber,colors.coral,colors.teal][i]}"/><text x="28" y="8" text-anchor="middle" class="micro" fill="${colors.white}">${x[0]}</text><text x="82" y="9" class="label">${esc(x[1])}</text></g>`).join('')}</g>`,foot:'Môi trường: Python zipfile/hashlib + Androguard 4.1.4; phân tích offline; không cài/khởi chạy APK.'},
 {kicker:'07 / KẾT LUẬN & RANH GIỚI',title:['BẰNG CHỨNG TỐT','BẮT ĐẦU TỪ ĐIỀU CHƯA BIẾT.'],sub:'Đây là baseline có thể tái lập — không phải chứng nhận an toàn, báo cáo pháp chứng toàn diện hay kiểm toán backend.',visual:`<g transform="translate(1165 325)"><rect width="535" height="115" rx="18" fill="#153329" stroke="${colors.teal}" stroke-opacity=".7"/><text x="34" y="48" class="label" fill="${colors.teal}">CÓ THỂ NÓI</text><text x="34" y="86" class="small">Cấu trúc · metadata · chuỗi · chữ ký quan sát được</text><rect y="145" width="535" height="145" rx="18" fill="#382624" stroke="${colors.coral}" stroke-opacity=".7"/><text x="34" y="193" class="label" fill="${colors.coral}">CHƯA THỂ KẾT LUẬN</text><text x="34" y="232" class="small">Hành vi runtime · mục đích dữ liệu · an toàn tổng thể</text><text x="34" y="268" class="small">· tính xác thực của nguồn phân phối</text>${pill(0,380,'ĐỐI CHIẾU SIGNER · CHẠY TEST CÓ KIỂM SOÁT · CÔNG BỐ GIỚI HẠN',colors.amber)}</g>`,foot:'Bản nghiên cứu: research/report.vi.md · Dữ liệu chỉ mô tả đúng artifact đầu vào, không khẳng định nguồn gốc chính thức.'}
];

function wrapLine(s:string,max=52){const words=s.split(/\s+/);const lines:string[]=[];let line='';for(const word of words){if(line&&`${line} ${word}`.length>max){lines.push(line);line=word;}else line=line?`${line} ${word}`:word;}if(line)lines.push(line);return lines;}
function svgScene(s:typeof scenes[number],idx:number){
 const stars=Array.from({length:72},(_,i)=>{const x=(i*239+idx*83)%W,y=(i*419+idx*131)%H, r=(i%3)+1;return `<circle cx="${x}" cy="${y}" r="${r}" fill="#c7e4f5" opacity=".${i%4+1}"/>`;}).join('');
 const title=s.title.map((t,i)=>`<text x="150" y="${380+i*112}" class="title">${esc(t)}</text>`).join('');
 return `<svg xmlns="http://www.w3.org/2000/svg" width="${W}" height="${H}" viewBox="0 0 ${W} ${H}"><defs><linearGradient id="bg" x1="0" y1="0" x2="1" y2="1"><stop stop-color="#07121e"/><stop offset=".55" stop-color="#0a1b29"/><stop offset="1" stop-color="#101c2d"/></linearGradient><radialGradient id="glow"><stop stop-color="#174c53" stop-opacity=".72"/><stop offset="1" stop-color="#08131f" stop-opacity="0"/></radialGradient><style>.title{font:700 73px 'DejaVu Sans';fill:${colors.white};letter-spacing:-2px}.sub{font:400 27px 'DejaVu Sans';fill:${colors.muted}}.kicker{font:700 18px 'DejaVu Sans';letter-spacing:4px;fill:${colors.teal}}.foot{font:400 17px 'DejaVu Sans';fill:#8097aa}.metric{font:700 58px 'DejaVu Sans'}.label{font:600 25px 'DejaVu Sans';fill:${colors.white}}.small{font:400 20px 'DejaVu Sans';fill:#b2c2d1}.micro{font:700 16px 'DejaVu Sans';letter-spacing:1px}.dark{fill:#07121e}.pill{font:700 16px 'DejaVu Sans';letter-spacing:1px}.mono{font:400 18px 'DejaVu Sans Mono';fill:#c8d8e4}</style></defs><rect width="100%" height="100%" fill="url(#bg)"/><ellipse cx="1450" cy="490" rx="670" ry="580" fill="url(#glow)"/>${stars}<path d="M0 930H1920" stroke="#203648" stroke-width="2"/><text x="150" y="130" class="kicker">${esc(s.kicker)}</text>${title}<text x="150" y="625" class="sub">${wrapLine(s.sub).map((line,i)=>`<tspan x="150" dy="${i===0?0:34}">${esc(line)}</tspan>`).join('')}</text><path d="M150 720h590" stroke="${colors.teal}" stroke-width="3" stroke-opacity=".65"/><text x="150" y="985" class="foot">${esc(s.foot)}</text><text x="1770" y="985" class="foot" text-anchor="end">ZP / STATIC STUDY · ${String(idx+1).padStart(2,'0')} — 08</text>${s.visual}</svg>`;
}

function writeScore(file:string,seconds:number){
 const sr=48000, channels=2, frames=sr*seconds, data=Buffer.alloc(frames*channels*2);
 // Original instrumental soundscape: warm sustained pads, a restrained low pulse, and glassy pentatonic plucks.
 // No voice samples or third-party audio. Frequencies and envelope are synthesized deterministically.
 const midi=(m:number)=>440*Math.pow(2,(m-69)/12);
 const progression=[[50,57,60,64],[46,53,57,60],[48,55,59,62],[45,52,57,60],[50,57,60,64],[53,60,64,67],[48,55,59,62],[50,57,60,64]];
 const scale=[62,65,67,69,72,74,77];
 for(let i=0;i<frames;i++){
  const t=i/sr, bar=Math.floor(t/SCENE)%progression.length, local=t%SCENE;
  const beat=t%1.25; let left=0,right=0;
  const chord=progression[bar];
  for(let j=0;j<chord.length;j++){
   const f=midi(chord[j]); const slow=0.62+0.38*Math.sin(2*Math.PI*(0.035+j*.006)*t+j);
   const phase=2*Math.PI*f*t; const tone=Math.sin(phase)+0.18*Math.sin(2*phase)+0.06*Math.sin(3*phase);
   const v=tone*slow*0.036; left+=v*(j%2?0.78:1); right+=v*(j%2?1:0.78);
  }
  const bassEnv=Math.pow(Math.max(0,1-beat/0.48),2);
  const bass=Math.sin(2*Math.PI*midi(chord[0]-12)*t)*bassEnv*0.085;left+=bass;right+=bass*0.94;
  const step=t%0.625, barStep=Math.floor(t/0.625); const note=scale[(barStep*3+Math.floor(bar*2))%scale.length];
  const pluckEnv=Math.exp(-step*8)*(1-Math.exp(-step*120)); const pf=midi(note+12);
  const pluck=(Math.sin(2*Math.PI*pf*t)+.24*Math.sin(2*Math.PI*pf*2.01*t))*pluckEnv*.038;
  left+=pluck*(step<.03?1:.88);right+=pluck*.91;
  // Every eight seconds, a quiet, short bell accent marks the editorial beat.
  const bellPos=t%8, bellEnv=Math.exp(-bellPos*5)*(1-Math.exp(-bellPos*90));
  const bell=Math.sin(2*Math.PI*midi(74)*t)*bellEnv*.026;left+=bell*.7;right+=bell;
  const fadeIn=Math.min(1,t/2),fadeOut=Math.min(1,(seconds-t)/3),master=fadeIn*fadeOut*.82;
  const soft=(x:number)=>Math.tanh(x*1.2)*master;
  for(let c=0;c<2;c++){const value=soft(c===0?left:right);data.writeInt16LE(Math.max(-32767,Math.min(32767,Math.round(value*32767))), (i*2+c)*2);}
 }
 const h=Buffer.alloc(44);h.write('RIFF',0);h.writeUInt32LE(36+data.length,4);h.write('WAVE',8);h.write('fmt ',12);h.writeUInt32LE(16,16);h.writeUInt16LE(1,20);h.writeUInt16LE(channels,22);h.writeUInt32LE(sr,24);h.writeUInt32LE(sr*channels*2,28);h.writeUInt16LE(channels*2,32);h.writeUInt16LE(16,34);h.write('data',36);h.writeUInt32LE(data.length,40);fs.writeFileSync(file,Buffer.concat([h,data]));
}
function run(cmd:string,args:string[]){const r=spawnSync(cmd,args,{stdio:'inherit',maxBuffer:1024*1024*20});if(r.error)throw r.error;if(r.status!==0)throw new Error(`${cmd} exited with ${r.status}`);}

fs.mkdirSync(FRAMES,{recursive:true});fs.mkdirSync(TMP,{recursive:true});
const sharp=(await import('sharp')).default;
for(let i=0;i<scenes.length;i++){const svg=path.join(FRAMES,`scene-${String(i+1).padStart(2,'0')}.svg`);const png=path.join(FRAMES,`scene-${String(i+1).padStart(2,'0')}.png`);const source=svgScene(scenes[i],i);fs.writeFileSync(svg,source);await sharp(Buffer.from(source)).png().toFile(png);}
const audio=path.join(TMP,'original-score.wav');writeScore(audio,SCENE*scenes.length);
const ffmpeg=path.join(ROOT,'video/node_modules/@ffmpeg-installer/linux-x64/ffmpeg');
if(!fs.existsSync(ffmpeg))throw new Error('ffmpeg static dependency missing. Run npm ci in video/.');
const args:string[]=['-y','-hide_banner','-loglevel','warning'];
scenes.forEach((_,i)=>args.push('-loop','1','-framerate',String(FPS),'-t',String(SCENE),'-i',path.join(FRAMES,`scene-${String(i+1).padStart(2,'0')}.png`)));
args.push('-i',audio,'-filter_complex');
let filters=scenes.map((_,i)=>`[${i}:v]zoompan=z='min(zoom+0.00035,1.035)':d=1:s=${W}x${H}:fps=${FPS},setsar=1,fade=t=in:st=0:d=0.35,fade=t=out:st=${SCENE-0.42}:d=0.42,format=yuv420p[v${i}]`).join(';');
filters+=`;${scenes.map((_,i)=>`[v${i}]`).join('')}concat=n=${scenes.length}:v=1:a=0,format=yuv420p[vout]`;
args.push(filters,'-map','[vout]','-map',`${scenes.length}:a:0`,'-t',String(SCENE*scenes.length),'-c:v','libx264','-preset','fast','-crf','19','-profile:v','high','-level','4.1','-pix_fmt','yuv420p','-af','loudnorm=I=-18:TP=-1.5:LRA=9','-c:a','aac','-b:a','256k','-ar','48000','-ac','2','-movflags','+faststart',path.join(OUT,'Zalopay_Static_Reverse_Engineering.vi.mp4'));
run(ffmpeg,args);
console.log(`Wrote ${path.relative(ROOT,path.join(OUT,'Zalopay_Static_Reverse_Engineering.vi.mp4'))}`);
